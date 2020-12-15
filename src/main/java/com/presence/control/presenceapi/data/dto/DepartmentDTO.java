package com.presence.control.presenceapi.data.dto;

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

    @JsonProperty(required = true)
    @NotNull
    private String departmentName;

    @JsonProperty(required = true)
    @NotNull
    private Integer maxPeopleAllowed;

    @JsonProperty(required = true)
    @NotNull
    private Long departmentLocalId;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long ownerUserId;


}
