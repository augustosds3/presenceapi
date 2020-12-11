package com.presence.control.presenceapi.application.api.v1.useroperations;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/api/useroperations/v1/")
@Api(value = "Presence Control User Operations API")
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "User created"),
        @ApiResponse(code = 409, message = "User already exists with specified email"),
        @ApiResponse(code = 400, message = "Wrong parameters in the request message")
})
public interface UserOperationsController {

    @ApiOperation("Create a new User")
    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    ResponseEntity<Response> signUp(@Valid @RequestBody UserDTO user);

}
