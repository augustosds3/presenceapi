package com.presence.control.presenceapi.infrastructure.repository.local;

import com.presence.control.presenceapi.domain.entity.Local;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LocalRepository extends CrudRepository<Local, Long> {

    Long countByLocalName(String localName);

    List<Local> findAllBySubscribedUsers_Id(Long ownerUserId);
}
