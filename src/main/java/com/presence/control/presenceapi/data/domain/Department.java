package com.presence.control.presenceapi.data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department extends BaseEntity {

    private String departmentName;

    private Long maxPeopleAllowed;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Local departmentLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerUser;
}
