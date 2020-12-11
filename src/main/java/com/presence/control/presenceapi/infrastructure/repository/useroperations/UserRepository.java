package com.presence.control.presenceapi.infrastructure.repository.useroperations;

import com.presence.control.presenceapi.data.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
