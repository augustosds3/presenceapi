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
public class Local extends BaseEntity{

    private String localName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerUser;

    @ManyToMany(mappedBy = "subscribedLocals", cascade = CascadeType.ALL)
    private Set<User> subscribedUsers;

    @OneToMany(mappedBy = "departmentLocal")
    private List<Department> localDepartments;


}
