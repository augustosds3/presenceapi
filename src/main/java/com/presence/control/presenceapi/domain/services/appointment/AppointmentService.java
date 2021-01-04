package com.presence.control.presenceapi.domain.services.appointment;

import com.presence.control.presenceapi.domain.entity.Appointment;
import com.presence.control.presenceapi.application.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDTO> registerUserAppointments(List<Appointment> appointments, Long userId);

    List<AppointmentDTO> findAllAppointments();

    List<AppointmentDTO> findAllUserAppointments(Long userId);

}
