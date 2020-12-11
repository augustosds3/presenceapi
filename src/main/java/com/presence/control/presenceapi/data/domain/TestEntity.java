package com.presence.control.presenceapi.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TestEntity extends BaseEntity {

    private String testField;

    private String testField2;

}
