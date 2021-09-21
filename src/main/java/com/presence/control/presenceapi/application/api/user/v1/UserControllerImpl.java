package com.presence.control.presenceapi.application.api.user.v1;

import com.presence.control.presenceapi.application.response.Response;
import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.UserDTO;
import com.presence.control.presenceapi.domain.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final ConversionMapper conversionMapper;

    @Override
    public ResponseEntity<Response> signUp(UserDTO user) {

        UserDTO createdUser = userService.registerUser(conversionMapper.map(user, User.class));

        Response userResponse = new Response();
        userResponse.setMessage("User Created");
        userResponse.setResponseObject(createdUser);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }



}
