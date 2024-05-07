package com.samill.missionary_backend.member;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.member.dto.PostTokenRequest;
import com.samill.missionary_backend.member.dto.PutUserRequest;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberGatewayManagementTests extends AbstractControllerTest {

    protected final String snippetPath = "{class-name}/{method-name}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    @DisplayName("user sign up test")
    @Transactional
    public void putUserTest() {
        var request = PutUserRequest.builder()
            .name("정한별")
            .phoneNumber("01084708097")
            .birthDate("19941027")
            .gender("남")
            .loginId("hanbyul.jung")
            .password("samil123!@#")
            .email("samil@test.com")
            .is_baptized(Boolean.TRUE)
            .baptizedAt(OffsetDateTime.now())
            .build();

        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(post(CREATE_USER_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        });
    }

    @Test
    @DisplayName("user login test")
    @Transactional
    public void postTokenTest() {
        var request = PostTokenRequest.builder()
            .loginId("hanbyul.jung")
            .password("samil123!@#")
            .build();

        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(post(USER_LOGIN_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        });
    }
}
