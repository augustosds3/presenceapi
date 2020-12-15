package com.presence.control.presenceapi.infrastructure.repository.local;

import com.presence.control.presenceapi.data.domain.Local;
import org.springframework.data.repository.CrudRepository;

public interface LocalRepository extends CrudRepository<Local, Long> {

    Long countByLocalName(String localName);
}
