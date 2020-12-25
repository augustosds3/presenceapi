package com.presence.control.presenceapi.infrastructure.repository.appointment;

import com.presence.control.presenceapi.data.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAllByAppointmentUser_Id(Long userId);

}
