package com.presence.control.presenceapi.domain.services.appointment;

import com.presence.control.presenceapi.data.domain.Appointment;
import com.presence.control.presenceapi.data.dto.AppointmentDTO;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDTO> registerUserAppointments(List<Appointment> appointments);

    List<AppointmentDTO> findAllAppointments();

    List<AppointmentDTO> findAllUserAppointments(Long userId);

}
