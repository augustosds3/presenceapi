package com.presence.control.presenceapi.application.api.v1.local;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import com.presence.control.presenceapi.domain.services.local.LocalService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class LocalControllerImpl implements LocalController {

    LocalService localService;
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> createLocal(@Valid LocalDTO local) {

        LocalDTO createdLocal = localService.createLocal(modelMapper.map(local, Local.class), local.getOwnerUserId());

        Response localResponse = new Response();
        localResponse.setMessage("Local Created");
        localResponse.setResponseObject(createdLocal);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> subscribeUser(Long localId, Long userID) {

        String result = localService.subscribeUserToLocal(localId, userID);

        Response localResponse = new Response();
        localResponse.setMessage(result);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Response> findAllUserLocals(Long userId) {

        List<LocalDTO> userLocals = localService.findAllUserLocals(userId);

        Response localResponse = new Response();
        localResponse.setMessage("User Locals found");
        localResponse.setResponseObject(userLocals);

        return new ResponseEntity<>(localResponse, HttpStatus.OK);
    }
}
