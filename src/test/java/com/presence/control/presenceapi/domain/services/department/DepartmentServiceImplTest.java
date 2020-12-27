package com.presence.control.presenceapi.domain.services.department;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import com.presence.control.presenceapi.domain.exception.DepartmentAlreadyExists;
import com.presence.control.presenceapi.infrastructure.repository.department.DepartmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    LocalRepository localRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    ConversionMapper conversionMapper;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    ArgumentCaptor<Department> departmentArgumentCaptor = ArgumentCaptor.forClass(Department.class);

    private ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);


    @Test
    public void shouldCreateDepartmentSuccessfully(){
        given(departmentRepository.countByDepartmentNameAndDepartmentLocal_Id(anyString(), anyLong())).willReturn(0L);
        given(localRepository.findById(anyLong())).willReturn(Optional.of(getTestLocal()));
        given(userRepository.findById(anyLong())).willReturn(Optional.of(getTestUser()));
        given(departmentRepository.save(any())).willReturn(getTestDepartment());
        given(conversionMapper.map(any(), any())).willReturn(getTestDepartmentDTO());

        DepartmentDTO createdDepartment = departmentService.createDepartment(getTestDepartment(), getTestLocal().getId());

        assertNotNull(createdDepartment);

        verify(departmentRepository).save(departmentArgumentCaptor.capture());

        Department passedDepartment = departmentArgumentCaptor.getValue();

        assertEquals(createdDepartment.getDepartmentLocalId(), passedDepartment.getDepartmentLocal().getId());
        assertEquals(createdDepartment.getOwnerUserId(), passedDepartment.getOwnerUser().getId());
        assertEquals(createdDepartment.getMaxPeopleAllowed(), passedDepartment.getMaxPeopleAllowed());
    }

    @Test
    public void whenDepartmentExistsShouldThrowDepartmentAlreadyExists(){
        given(departmentRepository.countByDepartmentNameAndDepartmentLocal_Id(anyString(), anyLong())).willReturn(1L);

        Exception exception = assertThrows(DepartmentAlreadyExists.class, () -> departmentService.createDepartment(getTestDepartment(),getTestLocal().getId()));

        assertEquals(exception.getMessage(), String.format("Department with name %s already exist", getTestDepartment().getDepartmentName()));
    }

    @Test
    public void shouldSubscribeUserToDepartmentlSuccessfully(){
        Department testDepartment = getTestDepartment();
        User testUser = getTestUser();

        given(departmentRepository.findById(anyLong())).willReturn(Optional.of(testDepartment));
        given(userRepository.findById(anyLong())).willReturn(Optional.of(testUser));
        given(userRepository.save(any(User.class))).willReturn(testUser);

        String creationMessage = departmentService.subscribeUser(getTestLocal().getId(), getTestUser().getId());

        assertEquals("User Subscribed", creationMessage);

        verify(userRepository).save(userArgumentCaptor.capture());

        User passedUser = userArgumentCaptor.getValue();
        assertEquals(testUser.getEmail(), passedUser.getEmail());
        assertEquals(testUser.getFullName(), passedUser.getFullName());
        assertEquals(testUser.getPassword(), passedUser.getPassword());
        assertTrue(testUser.getSubscribedDepartments().contains(testDepartment));
    }

    @Test
    public void shouldFindAllUserSubscribedDepartments(){
        given(departmentRepository.findAllBySubscribedUsers_Id(anyLong())).willReturn(getDepartmentList());
        given(conversionMapper.mapList(anyList(), any())).willReturn(getDepartmentDTOList());

        List<DepartmentDTO> userSubscribedDepartments = departmentService.findAllUserDepartments(anyLong());

        assertEquals(getDepartmentList().size(), userSubscribedDepartments.size());
    }

    private Department getTestDepartment(){
        Department testDepartment = new Department();
        testDepartment.setId(1L);
        testDepartment.setDepartmentName("TestDepartment");
        testDepartment.setMaxPeopleAllowed(10);

        return testDepartment;
    }

    private DepartmentDTO getTestDepartmentDTO(){
        DepartmentDTO testDepartmentDTO = new DepartmentDTO();
        testDepartmentDTO.setId(1L);
        testDepartmentDTO.setDepartmentName("TestDepartment");
        testDepartmentDTO.setMaxPeopleAllowed(10);
        testDepartmentDTO.setDepartmentLocalId(1L);
        testDepartmentDTO.setOwnerUserId(1L);

        return testDepartmentDTO;
    }

    private Local getTestLocal(){
        Local testLocal = new Local();
        testLocal.setId(1L);
        testLocal.setLocalName("Local1");
        testLocal.setOwnerUser(getTestUser());

        return testLocal;
    }

    private User getTestUser(){
        User testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");
        testUser.setSubscribedDepartments(new HashSet<>());

        return testUser;
    }

    private List<Department> getDepartmentList(){
        return Arrays.asList(getTestDepartment());
    }

    private List<Object> getDepartmentDTOList() {
        return Arrays.asList(getTestDepartmentDTO());
    }

}