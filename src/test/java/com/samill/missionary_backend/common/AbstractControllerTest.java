package com.samill.missionary_backend.common;

import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.ADMIN_LOGIN_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.gateway.dto.ApiResponse;
import com.samill.missionary_backend.gateway.dto.LoginAdminRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public abstract class AbstractControllerTest {

    protected final String snippetPath = "{class-name}/{method-name}";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper jacksonObjectMapper;

    protected String getAuthorizationUserOfHeader() throws Exception {
        var request = LoginUserRequest.builder()
            .loginId("hanbyul.jung")
            .password("samil123!@#")
            .build();
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.post(USER_LOGIN_URI)
                .content(jacksonObjectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        var responseBody = resultActions.getResponse().getContentAsString();
        var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap<?, ?>) apiResponse.getData()).get("token"));
    }

    protected String getAuthorizationAdminOfHeader() throws Exception {
        var request = LoginAdminRequest.builder()
            .loginId("admin_test")
            .password("samil123!@#")
            .build();

        var resultActions = mockMvc.perform(MockMvcRequestBuilders.post(ADMIN_LOGIN_URI)
                .content(jacksonObjectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        var responseBody = resultActions.getResponse().getContentAsString();
        var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap<?, ?>) apiResponse.getData()).get("token"));
    }

    protected String getBearerJwtToken(String superToken) {
        return "Bearer" + " " + superToken;

    }
}
