package com.presence.control.presenceapi.application.api.v1.department;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.application.dto.DepartmentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/department")
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

    @PostMapping(path = "/subscribe-user")
    @ApiOperation(value = "Subscribe a User to a Department")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Subscribed"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> subscribeUser(@RequestParam(name = "departmentId") Long departmentId, @RequestParam(name = "userId") Long userID);

    @GetMapping(path = "/departments")
    @ApiOperation(value = "Return a List of all registered Locals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Departments found"),
            @ApiResponse(code = 204, message = "No Departments found"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> findAllDepartments();


    @GetMapping(path = "/departments/{userId}")
    @ApiOperation(value = "Return a List of User subscribed Locals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Departments found"),
            @ApiResponse(code = 204, message = "No User Departments found"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> findAllUserDepartments(@PathVariable Long userId);

}
