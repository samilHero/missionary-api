package com.samill.missionary_backend.gateway;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.gateway.dto.ApiResponse;
import com.samill.missionary_backend.member.dto.TokenDto;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "hanbyul.jung")
public class ApiResponseTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    public void getApiResponseTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", getAuthorizationOfHeader()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String getAuthorizationOfHeader() throws Exception {
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var responseBody = resultActions.getResponse().getContentAsString();
        var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap) apiResponse.getData()).get("token"));
    }
}
