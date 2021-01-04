package com.presence.control.presenceapi.infrastructure.repository.user;

import com.presence.control.presenceapi.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    Long countByEmail(String email);

}
