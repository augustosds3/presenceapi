package com.presence.control.presenceapi.domain.services.department;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.Department;
import com.presence.control.presenceapi.domain.entity.Local;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.DepartmentDTO;
import com.presence.control.presenceapi.domain.exception.DepartmentAlreadyExists;
import com.presence.control.presenceapi.domain.exception.LocalNotFoundException;
import com.presence.control.presenceapi.domain.exception.UserNotFoundException;
import com.presence.control.presenceapi.infrastructure.repository.department.DepartmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    UserRepository userRepository;
    LocalRepository localRepository;
    DepartmentRepository departmentRepository;
    ConversionMapper conversionMapper;

    @Override
    public DepartmentDTO createDepartment(Department department, Long departmentLocalId) {

        if(departmentExistsInLocal(department.getDepartmentName(), departmentLocalId)){
            throw new DepartmentAlreadyExists(String.format("Department with name %s already exist", department.getDepartmentName()));
        }

        Optional<Local> departmentLocalOptional = localRepository.findById(departmentLocalId);

        department.setDepartmentLocal(departmentLocalOptional
                .orElseThrow(() -> new LocalNotFoundException(String.format("Error during Department creation: local with id %d not found.", departmentLocalId ))));

        Optional<User> ownerUserOptional = userRepository.findById(department.getDepartmentLocal().getOwnerUser().getId());

        department.setOwnerUser(ownerUserOptional
                .orElseThrow(() -> new UserNotFoundException(String.format("Error during Local creation: user with id %d not found.", department.getDepartmentLocal().getOwnerUser().getId() ))));

        Department createdDepartment = departmentRepository.save(department);

        return conversionMapper.map(createdDepartment, DepartmentDTO.class);
    }

    @Override
    @Transactional
    public String subscribeUser(Long departmentId, Long userId) {

        Department department = departmentRepository.findById(departmentId).get();
        User user = userRepository.findById(userId).get();

        user.getSubscribedDepartments().add(department);

        userRepository.save(user);

        return "User Subscribed";
    }

    @Override
    public List<DepartmentDTO> findAllDepartments() {

        List<Department> allDepartments = new ArrayList<>();

        departmentRepository.findAll().forEach(allDepartments::add);

        return conversionMapper.mapList(allDepartments, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> findAllUserDepartments(Long userId) {

        List<Department> userDepartments = departmentRepository.findAllBySubscribedUsers_Id(userId);

        return conversionMapper.mapList(userDepartments, DepartmentDTO.class);
    }

    private boolean departmentExistsInLocal(String departmentName, Long departmentLocalId) {

        return departmentRepository.countByDepartmentNameAndDepartmentLocal_Id(departmentName, departmentLocalId) > 0;

    }
}
