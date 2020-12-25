package com.presence.control.presenceapi.application.api.v1.appointment;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.dto.AppointmentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "/api/appointment/v1")
@Api(value = "Presence Control Appointment Operations API")
public interface AppointmentController {

    @PostMapping(path = "/register-appointment", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Register a new appointment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Appointments created"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> registerAppointments(@Valid @RequestBody List<AppointmentDTO> appointments);

    @GetMapping(path = "/appointments")
    @ApiOperation(value = "Return a List of all registered Appointments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Appointments found"),
            @ApiResponse(code = 204, message = "No Appointments found"),
    })
    ResponseEntity<Response> findAllAppointments();

    @GetMapping(path = "/appointments/{userId}")
    @ApiOperation(value = "Return a List of User Appointments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Appointments found"),
            @ApiResponse(code = 204, message = "No User Appointments found"),
            @ApiResponse(code = 400, message = "Wrong parameters in the request message")
    })
    ResponseEntity<Response> findAllUserAppointments(@PathVariable Long userId);

}
