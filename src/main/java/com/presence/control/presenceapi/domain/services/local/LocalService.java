package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.domain.entity.Local;
import com.presence.control.presenceapi.application.dto.LocalDTO;

import java.util.List;

public interface LocalService {

    LocalDTO createLocal(Local local, Long ownerId);

    String subscribeUserToLocal(Long localId, Long userId);

    List<LocalDTO> findAllLocals();

    List<LocalDTO> findAllUserLocals(Long userId);

}
