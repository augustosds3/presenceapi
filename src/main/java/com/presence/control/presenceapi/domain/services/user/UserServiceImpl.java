package com.presence.control.presenceapi.domain.services.user;

import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;
import com.presence.control.presenceapi.domain.exception.UserAlreadyExistsException;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) {

        if(emailExists(user)){
            throw new UserAlreadyExistsException("An account already exist with email address: " + user.getEmail());
        }

        User createdUser = userRepository.save(user);

        return modelMapper.map(createdUser, UserDTO.class);
    }

    private boolean emailExists(User user) {

       return userRepository.findByEmail(user.getEmail()) != null;


    }


}
