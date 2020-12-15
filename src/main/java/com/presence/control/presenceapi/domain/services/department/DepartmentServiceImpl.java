package com.presence.control.presenceapi.domain.services.department;

import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;
import com.presence.control.presenceapi.domain.exception.DepartmentAlreadyExists;
import com.presence.control.presenceapi.domain.exception.LocalAlreadyExistsException;
import com.presence.control.presenceapi.domain.exception.LocalNotFoundException;
import com.presence.control.presenceapi.domain.exception.UserNotFoundException;
import com.presence.control.presenceapi.infrastructure.repository.department.DepartmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    UserRepository userRepository;
    LocalRepository localRepository;
    DepartmentRepository departmentRepository;
    ModelMapper modelMapper;

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

        return modelMapper.map(createdDepartment, DepartmentDTO.class);
    }

    private boolean departmentExistsInLocal(String departmentName, Long departmentLocalId) {

        return departmentRepository.countByDepartmentNameAndDepartmentLocal_Id(departmentName, departmentLocalId) > 0;

    }
}
