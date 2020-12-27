package com.presence.control.presenceapi.domain.services.appointment;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Appointment;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.AppointmentDTO;
import com.presence.control.presenceapi.infrastructure.repository.appointment.AppointmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentServiceImpl implements AppointmentService{

    UserRepository userRepository;
    AppointmentRepository appointmentRepository;
    ConversionMapper conversionMapper;

    @Override
    @Transactional
    public List<AppointmentDTO> registerUserAppointments(List<Appointment> appointments, Long userId) {

        User user = userRepository.findById(userId).get();

        appointments.forEach( appointment -> {
            user.getAppointmentsMade().add(appointment);
        });

        userRepository.save(user);

        return conversionMapper.mapList(appointments, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> findAllAppointments() {

        List<Appointment> allAppointments = new ArrayList<>();

        appointmentRepository.findAll().forEach(allAppointments::add);

        return conversionMapper.mapList(allAppointments, AppointmentDTO.class);
    }

    @Override
    public List<AppointmentDTO> findAllUserAppointments(Long userId) {

        List<Appointment> userAppointments = appointmentRepository.findAllByAppointmentUser_Id(userId);

        return conversionMapper.mapList(userAppointments, AppointmentDTO.class);
    }


}
