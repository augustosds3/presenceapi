package com.presence.control.presenceapi.application.api.v1.local;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.application.dto.LocalDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/local/v1")
@Api(value = "Presence Control Local Operations API")
public interface LocalController {

    @PostMapping(path = "/create-local", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create a new Local")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Local created"),
            @ApiResponse(code = 409, message = "Local already exists with specified name"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> createLocal(@Valid @RequestBody LocalDTO local);


    @PostMapping(path = "/subscribe-user")
    @ApiOperation(value = "Subscribe a User to a Local")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Subscribed"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> subscribeUser(@RequestParam(name = "localId") Long localId, @RequestParam(name = "userId") Long userID);

    @GetMapping(path = "/locals")
    @ApiOperation(value = "Return a List of all registered Locals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Locals found"),
            @ApiResponse(code = 204, message = "No Locals found"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> findAllLocals();


    @GetMapping(path = "/locals/{userId}")
    @ApiOperation(value = "Return a List of User subscribed Locals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User locals found"),
            @ApiResponse(code = 204, message = "No User Locals found"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> findAllUserLocals(@PathVariable Long userId);

}
