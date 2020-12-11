package com.presence.control.presenceapi.domain.services.useroperations;

import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;
import com.presence.control.presenceapi.infrastructure.repository.useroperations.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOperationsServiceImpl implements UserOperationsService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDTO registerUser(User user) {

        user.getId().equals(2);

        User createdUser = userRepository.save(user);

        return modelMapper.map(createdUser, UserDTO.class);
    }


}
