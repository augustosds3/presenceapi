package com.presence.control.presenceapi.domain.services.user;

import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.UserDTO;

public interface UserService {

    UserDTO registerUser(User user);

}
