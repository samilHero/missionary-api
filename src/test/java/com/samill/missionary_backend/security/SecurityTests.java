package com.samill.missionary_backend.security;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.common.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    @WithMockUser(username = "hanbyul.jung")
    public void authenticatedAccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_LOGIN_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getAuthorizationOfHeader()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void unauthenticatedAccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_USER_URI)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
