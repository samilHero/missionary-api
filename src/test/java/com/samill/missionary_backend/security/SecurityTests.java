package com.samill.missionary_backend.security;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;

import com.samill.missionary_backend.common.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SecurityTests extends AbstractControllerTest {

    @Test
    @WithMockUser(username = "hanbyul.jung")
    public void authenticatedAccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_LOGIN_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getAuthorizationUserOfHeader()))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void unauthenticatedAccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_USER_URI)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
