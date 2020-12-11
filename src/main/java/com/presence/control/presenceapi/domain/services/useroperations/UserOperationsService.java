package com.presence.control.presenceapi.domain.services.useroperations;

import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;

public interface UserOperationsService {

    UserDTO registerUser(User user);

}
