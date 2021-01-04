package com.presence.control.presenceapi.application.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonProperty(required = true)
    @NotNull
    private String fullName;

    @JsonProperty(required = true)
    @NotNull
    private String email;

    @JsonProperty(required = true)
    @NotNull
    private String password;
}
