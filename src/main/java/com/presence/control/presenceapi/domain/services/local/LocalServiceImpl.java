package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.Local;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.LocalDTO;
import com.presence.control.presenceapi.domain.exception.LocalAlreadyExistsException;
import com.presence.control.presenceapi.domain.exception.UserNotFoundException;
import com.presence.control.presenceapi.infrastructure.repository.local.LocalRepository;
import com.presence.control.presenceapi.infrastructure.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocalServiceImpl implements LocalService {

    UserRepository userRepository;
    LocalRepository localRepository;
    ConversionMapper conversionMapper;

    @Override
    public LocalDTO createLocal(Local local, Long ownerId) {

        if(localExists(local.getLocalName())){
            throw new LocalAlreadyExistsException(String.format("Local with name %s already exists.", local.getLocalName()));
        }

        Optional<User> ownerUserOptional = userRepository.findById(ownerId);

        local.setOwnerUser(ownerUserOptional.
                orElseThrow(() -> new UserNotFoundException(String.format("Error during Local creation: user with id %d not found.", ownerId ))));

       Local createdLocal = localRepository.save(local);

        return conversionMapper.map(createdLocal, LocalDTO.class);
    }

    @Override
    @Transactional
    public String subscribeUserToLocal(Long localId ,Long userId) {

        Local local = localRepository.findById(localId).get();

        User user = userRepository.findById(userId).get();

        user.getSubscribedLocals().add(local);

        userRepository.save(user);

        return "User Subscribed";
    }

    @Override
    public List<LocalDTO> findAllLocals() {

        List<Local> allLocals = new ArrayList<>();

        localRepository.findAll().forEach(allLocals::add);

        return conversionMapper.mapList(allLocals, LocalDTO.class);
    }

    @Override
    public List<LocalDTO> findAllUserLocals(Long userId) {

        List<Local> userLocals = localRepository.findAllBySubscribedUsers_Id(userId);

        return conversionMapper.mapList(userLocals, LocalDTO.class);
    }

    private boolean localExists(String localName) {
        return localRepository.countByLocalName(localName) > 0;
    }
}
