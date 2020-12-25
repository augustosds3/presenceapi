package com.presence.control.presenceapi.application.api.v1.department;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;
import com.presence.control.presenceapi.domain.services.department.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class DepartmentControllerImpl implements DepartmentController {

    private final DepartmentService departmentService;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> createDepartment(@Valid DepartmentDTO department) {

        DepartmentDTO createdDepartment = departmentService.createDepartment(modelMapper.map(department, Department.class), department.getDepartmentLocalId());

        Response localResponse = new Response();
        localResponse.setMessage("Department Created");
        localResponse.setResponseObject(createdDepartment);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> subscribeUser(Long departmentId, Long userID) {
        String result = departmentService.subscribeUser(departmentId, userID);

        Response localResponse = new Response();
        localResponse.setMessage(result);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> findAllDepartments() {

        List<DepartmentDTO> allDepartments = departmentService.findAllDepartments();
        Response departmentResponse = new Response();

        if(allDepartments.size() == 0 ){
            departmentResponse.setMessage("No registered Departments were found");
            return new ResponseEntity<>(departmentResponse, HttpStatus.NO_CONTENT);
        }

        departmentResponse.setMessage("All Departments found");
        departmentResponse.setResponseObject(allDepartments);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> findAllUserDepartments(Long userId) {
        List<DepartmentDTO> allUserDepartments = departmentService.findAllUserDepartments(userId);
        Response departmentResponse = new Response();

        if(allUserDepartments.size() == 0 ){
            departmentResponse.setMessage("User not registered in any department");
            return new ResponseEntity<>(departmentResponse, HttpStatus.NO_CONTENT);
        }

        departmentResponse.setMessage("Departments that user are registered found");
        departmentResponse.setResponseObject(allUserDepartments);
        return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
    }
}
