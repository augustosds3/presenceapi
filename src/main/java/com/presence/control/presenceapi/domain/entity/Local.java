package com.presence.control.presenceapi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Local extends BaseEntity{

    private String localName;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerUser;

    @ManyToMany(mappedBy = "subscribedLocals")
    private Set<User> subscribedUsers;

    @OneToMany(mappedBy = "departmentLocal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Department> localDepartments;


}
