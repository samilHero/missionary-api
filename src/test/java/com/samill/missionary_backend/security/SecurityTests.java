package com.samill.missionary_backend.security;

import static com.samill.missionary_backend.gateway.endPoint.MemberEndPoint.USER_LOGIN_URI;
import static com.samill.missionary_backend.gateway.endPoint.MemberEndPoint.USER_URI;

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
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URI)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    /*private String getAuthorizationOfHeader() throws Exception {
        var request = PutTokenRequest.builder()
            .id("5f5a0786-76cf-4467-bd10-8cab3b4dfafe")
            .build();
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.put(TOKEN_URI)
                .content(jacksonObjectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        var responseBody = resultActions.getResponse().getContentAsString();
        var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap) apiResponse.getData()).get("token"));
    }*/
}
