package com.presence.control.presenceapi.domain.services.user;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.UserDTO;
import com.presence.control.presenceapi.domain.exception.UserAlreadyExistsException;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConversionMapper conversionMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    private UserDTO testUserDto;

    private ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @BeforeEach
    public void setUp(){
        testUser = new User();
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");

        testUserDto = new UserDTO();
        testUserDto.setFullName("Augusto");
        testUserDto.setEmail("augusto@sfs.com");
        testUserDto.setPassword("test");
    }

    @Test
    public void shouldSaveUserSuccessfully(){
        final User user = testUser;

        given(userRepository.countByEmail(user.getEmail())).willReturn(0L);
        given(conversionMapper.map(any(), any())).willReturn(testUserDto);
        given(userRepository.save(user)).willAnswer(invocation -> invocation.getArgument(0));

        UserDTO createdUser = userService.registerUser(user);

        assertNotNull(createdUser);

        verify(userRepository).save(userArgumentCaptor.capture());

        User passedUser = userArgumentCaptor.getValue();
        assertEquals(createdUser.getEmail(), passedUser.getEmail());
        assertEquals(createdUser.getFullName(), passedUser.getFullName());
        assertEquals(createdUser.getPassword(), passedUser.getPassword());
    }

    @Test
    public void whenUserExistsShouldThrowUserAlreadyExistsException(){

        given(userRepository.countByEmail(anyString())).willReturn(1L);

        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(testUser));

        assertEquals(exception.getMessage(), "An account already exist with email address: " + testUser.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

}