package com.presence.control.presenceapi.data.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String fullName;

    private String email;

    private String password;

    @OneToMany(mappedBy = "ownerUser")
    private List<Local> ownedLocals;
}
