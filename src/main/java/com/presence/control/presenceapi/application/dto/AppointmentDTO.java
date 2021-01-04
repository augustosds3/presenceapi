package com.presence.control.presenceapi.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class AppointmentDTO {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotNull
    private String appointmentDate;

    @NotNull
    private String appointmentStartTime;

    @NotNull
    private String appointmentEndTime;

    @NotNull
    private Long appointmentUserId;

}
