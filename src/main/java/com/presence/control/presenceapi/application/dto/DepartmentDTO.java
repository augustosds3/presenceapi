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
public class DepartmentDTO {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotNull
    private String departmentName;

    @NotNull
    private Integer maxPeopleAllowed;

    @NotNull
    private Long departmentLocalId;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long ownerUserId;


}
