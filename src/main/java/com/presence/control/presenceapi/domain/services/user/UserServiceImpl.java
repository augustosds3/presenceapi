package com.presence.control.presenceapi.domain.services.user;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.UserDTO;
import com.presence.control.presenceapi.domain.exception.UserAlreadyExistsException;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ConversionMapper conversionMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) {

        if(emailExists(user)){
            throw new UserAlreadyExistsException("An account already exist with email address: " + user.getEmail());
        }

        User createdUser = userRepository.save(user);

        return conversionMapper.map(createdUser, UserDTO.class);
    }

    private boolean emailExists(User user) {

       return userRepository.countByEmail(user.getEmail()) > 0;


    }


}
