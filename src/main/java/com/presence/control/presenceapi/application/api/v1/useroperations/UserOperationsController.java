package com.presence.control.presenceapi.application.api.v1.useroperations;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(path = "/api/useroperations/v1/")
@Api(value = "Presence Control User Operations API")
public interface UserOperationsController {

    @ApiOperation("Create a new User")
    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    ResponseEntity<Response> signUp(@Valid @RequestBody UserDTO user);

}
