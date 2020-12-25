package com.presence.control.presenceapi.data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment extends BaseEntity {

    LocalDate appointmentDate;

    LocalTime appointmentStartTime;

    LocalTime appointmentEndTime;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User appointmentUser;

}
