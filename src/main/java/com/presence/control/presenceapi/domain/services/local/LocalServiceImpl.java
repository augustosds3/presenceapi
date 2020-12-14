package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import com.presence.control.presenceapi.domain.exception.UserNotFoundException;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalServiceImpl implements LocalService {

    UserRepository userRepository;
    LocalRepository localRepository;
    ModelMapper modelMapper;

    @Override
    public LocalDTO createLocal(Local local, Long ownerId) {

        Optional<User> ownerUserOptional = userRepository.findById(ownerId);

        local.setOwnerUser(ownerUserOptional.
                orElseThrow(() -> new UserNotFoundException(String.format("Error during Local creation: user with id %d not found.", ownerId ))));

       Local createdLocal = localRepository.save(local);

        return modelMapper.map(createdLocal, LocalDTO.class);
    }
}
