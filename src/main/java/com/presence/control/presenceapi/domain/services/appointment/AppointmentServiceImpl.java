package com.presence.control.presenceapi.domain.services.appointment;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Appointment;
import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.AppointmentDTO;
import com.presence.control.presenceapi.infrastructure.repository.department.DepartmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    UserRepository userRepository;
    DepartmentRepository departmentRepository;
    ConversionMapper conversionMapper;

    @Override
    @Transactional
    public List<AppointmentDTO> registerUserAppointments(List<Appointment> appointments) {

        Long userId = appointments.get(0).getAppointmentUser().getId();

        User user = userRepository.findById(userId).get();

        appointments.forEach( appointment -> {
            user.getAppointmentsMade().add(appointment);
        });

        userRepository.save(user);

        return conversionMapper.mapList(appointments, AppointmentDTO.class);
    }
}
