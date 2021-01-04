package com.presence.control.presenceapi.domain.services.appointment;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.Appointment;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.AppointmentDTO;
import com.presence.control.presenceapi.infrastructure.repository.appointment.AppointmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    AppointmentRepository appointmentRepository;

    @Mock
    ConversionMapper conversionMapper;

    @InjectMocks
    AppointmentServiceImpl appointmentService;

    ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

    @Test
    public void shouldCreateUserAppointmentSuccessfully(){

        given(userRepository.findById(anyLong())).willReturn(Optional.of(getTestUser()));
        given(userRepository.save(any(User.class))).willReturn(getTestUser());
        given(conversionMapper.mapList(any(), any())).willReturn(Arrays.asList(getTestAppointmentDTO()));

        List<AppointmentDTO> createdAppointments = appointmentService.registerUserAppointments(Arrays.asList(getTestAppointment()),getTestUser().getId());

        verify(userRepository).save(userArgumentCaptor.capture());

        User passedUser = userArgumentCaptor.getValue();

        assertTrue(passedUser.getAppointmentsMade().size() == createdAppointments.size());
    }

    private User getTestUser(){
        User testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");
        testUser.setAppointmentsMade(new HashSet<>());

        return testUser;
    }

    private Appointment getTestAppointment(){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse("26-12-1991", formatterDate);

        Appointment testAppointment = new Appointment();
        testAppointment.setId(1L);
        testAppointment.setAppointmentDate(date);
        testAppointment.setAppointmentStartTime(LocalTime.parse("09:00:00"));
        testAppointment.setAppointmentEndTime(LocalTime.parse("17:00:00"));
        testAppointment.setAppointmentUser(getTestUser());

        return testAppointment;

    }

    private AppointmentDTO getTestAppointmentDTO() {
        AppointmentDTO testAppointmentDTO = new AppointmentDTO();
        testAppointmentDTO.setAppointmentDate("26-12-1991");
        testAppointmentDTO.setAppointmentStartTime("09:00:00");
        testAppointmentDTO.setAppointmentEndTime("17:00:00");

        return testAppointmentDTO;

    }


}