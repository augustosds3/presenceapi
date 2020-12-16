package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.dto.LocalDTO;

import java.util.List;

public interface LocalService {

    LocalDTO createLocal(Local local, Long ownerId);

    String subscribeUserToLocal(Long localId, Long userId);

    List<LocalDTO> findAllUserLocals(Long userId);

}
