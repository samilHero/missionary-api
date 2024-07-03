package com.samill.missionary_backend.common;

import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.ADMIN_LOGIN_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.gateway.dto.ApiResponse;
import com.samill.missionary_backend.gateway.dto.LoginAdminRequest;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.util.annotation.NonNull;

public interface AbstractControllerTestsAuthorize {

    default String getAuthorizationUserOfHeader(@NonNull MockMvc mockMvc, @NonNull ObjectMapper jacksonObjectMapper) throws Exception {
        return this.getAuthorization(mockMvc, jacksonObjectMapper, USER_LOGIN_URI, "hanbyul.jung", "samil123!@#");
    }

    default String getAuthorizationAdminOfHeader(@NonNull MockMvc mockMvc, @NonNull ObjectMapper jacksonObjectMapper) throws Exception {
        return this.getAuthorization(mockMvc, jacksonObjectMapper, ADMIN_LOGIN_URI, "admin_test", "samil123!@#");
    }

    private String getAuthorization(
        @NonNull MockMvc mockMvc,
        @NonNull ObjectMapper jacksonObjectMapper,
        @NonNull String url,
        @NonNull String loginId,
        @NonNull String password
    )
        throws Exception {
        final var request = LoginAdminRequest.builder()
            .loginId(loginId)
            .password(password)
            .build();

        final var resultActions = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .content(jacksonObjectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        final var responseBody = resultActions.getResponse().getContentAsString();
        final var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap<?, ?>) apiResponse.getData()).get("token"));
    }
}
