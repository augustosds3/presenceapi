package com.presence.control.presenceapi.application.api.v1.department;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.dto.DepartmentDTO;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/api/department/v1")
@Api(value = "Presence Control Department Operations API")
public interface DepartmentController {

    @PostMapping(path = "/createdepartment", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a new Department")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Department created"),
            @ApiResponse(code = 409, message = "Department already exists with specified name"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> createDepartment(@Valid @RequestBody DepartmentDTO department);
}
