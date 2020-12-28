package com.presence.control.presenceapi.application.api.v1.local;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.presence.control.presenceapi.commons.helper.ConversionMapper;
import com.presence.control.presenceapi.data.domain.Local;
import com.presence.control.presenceapi.data.domain.User;
import com.presence.control.presenceapi.data.dto.LocalDTO;
import com.presence.control.presenceapi.domain.services.local.LocalService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LocalController.class)
class LocalControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConversionMapper conversionMapper;

    @MockBean
    private LocalService localService;

    @Test
    public void whenValidInputCreateLocalAndReturn200() throws Exception {
        LocalDTO testLocalDto = getTestLocalDTO();
        given(conversionMapper.map(any(LocalDTO.class), any())).willReturn(getTestLocal());
        given(localService.createLocal(any(Local.class), anyLong())).willReturn(testLocalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/local/v1/create-local").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLocalDto)))
                .andExpect(status().isCreated());
    }

  /*  @Test
    public void whenValidInputSubscribeUserThenReturn201() throws Exception{
        given(conversionMapper.map(any(LocalDTO.class), any())).willReturn(getTestLocal());
        given(localService.subscribeUserToLocal(anyLong(), anyLong())).willReturn("User Subscribed");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/local/v1/subscribe-user")
        .param("localId", "1")
                        .param("userID", "1")
                ).andExpect(status().isCreated());

    }*/


    private Local getTestLocal(){
        Local testLocal = new Local();
        testLocal.setId(1L);
        testLocal.setLocalName("Local1");
        testLocal.setOwnerUser(getTestUser());

        return testLocal;
    }

    private LocalDTO getTestLocalDTO(){
        LocalDTO testLocalDTO = new LocalDTO();
        testLocalDTO.setId(1L);
        testLocalDTO.setLocalName("Local1");
        testLocalDTO.setOwnerUserId(getTestUser().getId());

        return testLocalDTO;
    }

    private User getTestUser(){
        User testUser = new User();
        testUser.setId(1L);
        testUser.setFullName("Augusto");
        testUser.setEmail("augusto@sfs.com");
        testUser.setPassword("test");

        return testUser;
    }

}