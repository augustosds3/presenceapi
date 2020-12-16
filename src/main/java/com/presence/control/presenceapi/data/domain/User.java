package com.presence.control.presenceapi.data.domain;

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
public class User extends BaseEntity {

    private String fullName;

    private String email;

    private String password;

    @OneToMany(mappedBy = "ownerUser", fetch = FetchType.LAZY)
    private List<Local> ownedLocals;

    @OneToMany(mappedBy = "ownerUser", fetch = FetchType.LAZY)
    private List<Department> ownedDepartments;

    @ManyToMany
    @JoinTable (
            name = "local_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "local_id"))
    Set<Local> subscribedLocals;
}
