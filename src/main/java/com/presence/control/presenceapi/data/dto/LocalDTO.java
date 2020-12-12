package com.presence.control.presenceapi.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LocalDTO {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private Long ownerId;

    private String localName;

}
