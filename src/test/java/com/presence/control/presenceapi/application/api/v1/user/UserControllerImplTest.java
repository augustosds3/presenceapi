package com.presence.control.presenceapi.application.api.v1.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.domain.entity.User;
import com.presence.control.presenceapi.application.dto.UserDTO;
import com.presence.control.presenceapi.domain.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConversionMapper conversionMapper;

    @MockBean
    private UserService userService;


    @Test
    public void whenValidInputCreateUserAndReturn201() throws Exception {
        UserDTO testUserDTO = getTestUserDTO();
        given(conversionMapper.map(any(User.class), any())).willReturn(getTestUser());
        given(userService.registerUser(any(User.class))).willReturn(testUserDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/v1/signup").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(testUserDTO)))
                .andExpect(status().isCreated());
    }

    private User getTestUser(){
        User testUser = new User();
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");

        return testUser;
    }

    private UserDTO getTestUserDTO(){
        UserDTO testUserDto = new UserDTO();
        testUserDto.setFullName("Augusto");
        testUserDto.setEmail("augusto@sfs.com");
        testUserDto.setPassword("test");

        return testUserDto;
    }

}