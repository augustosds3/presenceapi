package com.presence.control.presenceapi.application.api.v1.department;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;
import com.presence.control.presenceapi.domain.services.department.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

    DepartmentService departmentService;
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> createDepartment(@Valid DepartmentDTO department) {

        DepartmentDTO createdDepartment = departmentService.createDepartment(modelMapper.map(department, Department.class), department.getDepartmentLocalId());

        Response localResponse = new Response();
        localResponse.setMessage("Department Created");
        localResponse.setResponseObject(createdDepartment);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }
}
