package com.presence.control.presenceapi.domain.services.local;

import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.dto.LocalDTO;

public interface LocalService {

    LocalDTO createLocal(Local local, Long ownerId);

}
