package com.presence.control.presenceapi.infrastructure.repository.appointment;

import com.presence.control.presenceapi.data.domain.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
}
