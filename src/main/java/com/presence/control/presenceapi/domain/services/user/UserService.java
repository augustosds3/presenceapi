package com.presence.control.presenceapi.domain.services.user;

import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;

public interface UserService {

    UserDTO registerUser(User user);

}
