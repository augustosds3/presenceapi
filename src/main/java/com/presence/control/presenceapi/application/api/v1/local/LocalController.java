package com.presence.control.presenceapi.application.api.v1.local;

import com.presence.control.presenceapi.application.response.Response;
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

@RequestMapping(path = "/api/local/v1")
@Api(value = "Presence Control Local Operations API")
public interface LocalController {

    @PostMapping(path = "/createlocal", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a new Local")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Local created"),
            @ApiResponse(code = 409, message = "Local already exists with specified name"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> createLocal(@Valid @RequestBody LocalDTO local);

}
