package com.presence.control.presenceapi.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    private String fullName;

    private String email;

    private String password;
}
