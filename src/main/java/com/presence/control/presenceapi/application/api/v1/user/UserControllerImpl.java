package com.presence.control.presenceapi.application.api.v1.user;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.UserDTO;
import com.presence.control.presenceapi.domain.services.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Response> signUp(UserDTO user) {

        UserDTO createdUser = userService.registerUser(modelMapper.map(user, User.class));

        Response userResponse = new Response();
        userResponse.setMessage("User Created");
        userResponse.setResponseObject(createdUser);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }



}