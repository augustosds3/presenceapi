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

@RestController
@AllArgsConstructor
public class LocalControllerImpl implements LocalController {

    LocalService localService;
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> createLocal(@Valid LocalDTO local) {

        LocalDTO createdLocal = localService.createLocal(modelMapper.map(local, Local.class), local.getOwnerId());

        Response localResponse = new Response();
        localResponse.setMessage("Local Created");
        localResponse.setResponseObject(createdLocal);

        return new ResponseEntity<>(localResponse, HttpStatus.CREATED);
    }
}
