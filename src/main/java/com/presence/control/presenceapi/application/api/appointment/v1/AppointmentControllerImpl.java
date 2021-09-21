package com.presence.control.presenceapi.application.api.appointment.v1;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.Appointment;
import com.presence.control.presenceapi.application.dto.AppointmentDTO;
import com.presence.control.presenceapi.domain.services.appointment.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AppointmentControllerImpl implements AppointmentController {

    private final AppointmentService appointmentService;

    private final ConversionMapper conversionMapper;

    @Override
    public ResponseEntity<Response> registerAppointments(@Valid List<AppointmentDTO> appointments, Long userId) {

        List<AppointmentDTO> createdAppointments = appointmentService.registerUserAppointments(conversionMapper.mapList(appointments, Appointment.class), userId);

        Response appointmentResponse = new Response();
        appointmentResponse.setMessage("Appointments Created");
        appointmentResponse.setResponseObject(createdAppointments);

        return new ResponseEntity<>(appointmentResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> findAllAppointments() {
        List<AppointmentDTO> allAppointments = appointmentService.findAllAppointments();

        Response appointmentResponse = new Response();
        appointmentResponse.setMessage("Appointments found");
        appointmentResponse.setResponseObject(allAppointments);

        return new ResponseEntity<>(appointmentResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> findAllUserAppointments(Long userId) {

        List<AppointmentDTO> userAppointments = appointmentService.findAllUserAppointments(userId);
        Response appointmentResponse = new Response();

        if(userAppointments.size() == 0){
            appointmentResponse.setMessage("No User Appointments found");
            return new ResponseEntity<>(appointmentResponse, HttpStatus.NO_CONTENT);
        }

        appointmentResponse.setMessage("Locals that user are registered found");
        appointmentResponse.setResponseObject(userAppointments);
        return new ResponseEntity<>(appointmentResponse, HttpStatus.OK);
    }

}
