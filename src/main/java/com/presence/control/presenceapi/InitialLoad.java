package com.presence.control.presenceapi;

import com.presence.control.presenceapi.data.domain.Department;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.infrastructure.repository.department.DepartmentRepository;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialLoad implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    LocalRepository localRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setEmail("augusto@gmail.com");
        user1.setFullName("Augusto Santos");
        user1.setPassword("testPass");

        User user2 = new User();
        user2.setEmail("sergio@gmail.com");
        user2.setFullName("Augusto Sergio");
        user2.setPassword("testPassWord");

        User user3 = new User();
        user3.setEmail("santos@gmail.com");
        user3.setFullName("Augusto Sergio dos Santos");
        user3.setPassword("testPassWorld");

        User user4 = new User();
        user4.setEmail("financeperson@gmail.com");
        user4.setFullName("Ze das finanças");
        user4.setPassword("testPassWorld");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);
        user4 = userRepository.save(user4);


        Local local1 = new Local();
        local1.setLocalName("Avenue Code");
        local1.setOwnerUser(user1);

        Local local2 = new Local();
        local2.setLocalName("EmpresaB");
        local2.setOwnerUser(user2);

        Local local3 = new Local();
        local3.setLocalName("EmpresaC");
        local3.setOwnerUser(user3);

        local1 = localRepository.save(local1);
        local2 = localRepository.save(local2);
        local3 = localRepository.save(local3);

        Department department1 = new Department();
        department1.setDepartmentName("Strikers");
        department1.setMaxPeopleAllowed(10);
        department1.setDepartmentLocal(local1);
        department1.setOwnerUser(user1);

        Department department2 = new Department();
        department2.setDepartmentName("Warriors");
        department2.setMaxPeopleAllowed(8);
        department2.setDepartmentLocal(local1);
        department2.setOwnerUser(user2);

        Department department3 = new Department();
        department3.setDepartmentName("Finances");
        department3.setMaxPeopleAllowed(8);
        department3.setDepartmentLocal(local2);
        department3.setOwnerUser(user4);

        Department department4 = new Department();
        department4.setDepartmentName("Office");
        department4.setMaxPeopleAllowed(5);
        department4.setDepartmentLocal(local3);
        department4.setOwnerUser(user3);

        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);
        departmentRepository.save(department4);

    }
}
