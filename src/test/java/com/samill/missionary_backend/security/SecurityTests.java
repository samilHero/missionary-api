package com.samill.missionary_backend.security;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    private String token;

    @Test
    @WithMockUser(username = "hanbyul.jung")
    public void testAuthenticatedAccess() throws Exception {
        getToken();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", StringUtils.join("Bearer ", token)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUnauthenticatedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    private void getToken() throws Exception {
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        token = resultActions.getResponse().getContentAsString();
        Assertions.assertNotNull(token);
    }
}
