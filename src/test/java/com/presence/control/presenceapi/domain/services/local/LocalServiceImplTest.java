package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import com.presence.control.presenceapi.domain.exception.LocalAlreadyExistsException;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocalServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    LocalRepository localRepository;

    @Mock
    ConversionMapper conversionMapper;

    @InjectMocks
    LocalServiceImpl localService;

    private ArgumentCaptor<Local> localArgumentCaptor = ArgumentCaptor.forClass(Local.class);

    private ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    public void shouldCreateLocalSuccessfully(){
        given(localRepository.countByLocalName(anyString())).willReturn(0L);
        given(userRepository.findById(anyLong())).willReturn(Optional.of(getTestUser()));
        given(localRepository.save(any())).willReturn(getTestLocal());
        given(conversionMapper.map(any(), any())).willReturn(getTestLocalDTO());

        LocalDTO createdLocal = localService.createLocal(getTestLocal(), getTestUser().getId());

        assertNotNull(createdLocal);

        verify(localRepository).save(localArgumentCaptor.capture());

        Local passedLocal = localArgumentCaptor.getValue();

        assertEquals(createdLocal.getLocalName(), passedLocal.getLocalName());
        assertEquals(createdLocal.getOwnerUserId(), passedLocal.getOwnerUser().getId());
    }

    @Test
    public void whenLocalExistsShouldThrowLocalAlreadyExistsException(){
        given(localRepository.countByLocalName(anyString())).willReturn(1L);

        Exception exception = assertThrows(LocalAlreadyExistsException.class, () -> localService.createLocal(getTestLocal(), anyLong()));

        assertEquals(exception.getMessage(), String.format("Local with name %s already exists.", getTestLocal().getLocalName()));
    }

    @Test
    public void shouldSubscribeUserToLocalSuccessfully(){
        Local testLocal = getTestLocal();
        User testUser = getTestUser();

        given(localRepository.findById(anyLong())).willReturn(Optional.of(testLocal));
        given(userRepository.findById(anyLong())).willReturn(Optional.of(testUser));
        given(userRepository.save(any(User.class))).willReturn(testUser);

        String creationMessage = localService.subscribeUserToLocal(getTestLocal().getId(), getTestUser().getId());

        assertEquals("User Subscribed", creationMessage);

        verify(userRepository).save(userArgumentCaptor.capture());

        User passedUser = userArgumentCaptor.getValue();
        assertEquals(testUser.getEmail(), passedUser.getEmail());
        assertEquals(testUser.getFullName(), passedUser.getFullName());
        assertEquals(testUser.getPassword(), passedUser.getPassword());
        assertTrue(testUser.getSubscribedLocals().contains(testLocal));
    }

    @Test
    public void shouldFindAllUserSubscribedLocals(){
        given(localRepository.findAllBySubscribedUsers_Id(anyLong())).willReturn(getUserLocalsList());
        given(conversionMapper.mapList(anyList(), any())).willReturn(getUserLocalsDTOList());

        List<LocalDTO> userSubscribedLocals = localService.findAllUserLocals(anyLong());

        assertEquals(getUserLocalsList().size(), userSubscribedLocals.size());
    }

    private Local getTestLocal(){
        Local testLocal = new Local();
        testLocal.setId(1L);
        testLocal.setLocalName("Local1");
        testLocal.setOwnerUser(getTestUser());

        return testLocal;
    }

    private LocalDTO getTestLocalDTO(){
        LocalDTO testLocalDTO = new LocalDTO();
        testLocalDTO.setId(1L);
        testLocalDTO.setLocalName("Local1");
        testLocalDTO.setOwnerUserId(getTestUser().getId());

        return testLocalDTO;
    }

    private User getTestUser(){
        User testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");
        testUser.setSubscribedLocals(new HashSet<>());

        return testUser;
    }


    private List<Local> getUserLocalsList() {
        return Arrays.asList(getTestLocal());
    }

    private List<Object> getUserLocalsDTOList() {
        return Arrays.asList(getTestLocalDTO());
    }



}