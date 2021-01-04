package com.presence.control.presenceapi.application.api.v1.user;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.application.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/user")
@Api(value = "Presence Control User Operations API")
public interface UserController {


    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 409, message = "User already exists with specified email"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> signUp(@Valid @RequestBody UserDTO user);

}
