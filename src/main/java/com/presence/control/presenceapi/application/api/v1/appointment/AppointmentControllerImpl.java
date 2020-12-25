package com.presence.control.presenceapi.application.api.v1.appointment;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Appointment;
import com.presence.control.presenceapi.data.dto.AppointmentDTO;
import com.presence.control.presenceapi.domain.services.appointment.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor
public class AppointmentControllerImpl implements AppointmentController {

    private final AppointmentService appointmentService;

    private final ConversionMapper conversionMapper;

    @Override
    public ResponseEntity<Response> registerAppointments(@Valid List<AppointmentDTO> appointments) {



        List<AppointmentDTO> createdAppointments = appointmentService.registerUserAppointments(conversionMapper.mapList(appointments, Appointment.class));

        Response appointmentResponse = new Response();
        appointmentResponse.setMessage("Appointments Created");
        appointmentResponse.setResponseObject(createdAppointments);

        return new ResponseEntity<>(appointmentResponse, HttpStatus.CREATED);
    }
/*
    public static void main(String args[]){

        ConversionMapper conversionMapper = new ConversionMapper();

        AppointmentDTO dto = new AppointmentDTO();
        dto.setAppointmentDate("20-12-2020");

        Appointment appointment = conversionMapper.map(dto, Appointment.class);

        System.out.println(appointment.getAppointmentDate());

    }
*/
}
