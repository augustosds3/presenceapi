package com.presence.control.presenceapi.application.api.v1.useroperations;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;
import com.presence.control.presenceapi.domain.services.useroperations.UserOperationsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserOperationsControllerImpl implements  UserOperationsController {

    private final UserOperationsService userOperationsService;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> signUp(UserDTO user) {

        UserDTO createdUser = userOperationsService.registerUser(modelMapper.map(user, User.class));

        Response<UserDTO> userResponse = new Response<>();
        userResponse.setPayload(createdUser);

        return ResponseEntity.ok(userResponse);
    }



}
