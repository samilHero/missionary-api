package com.samill.missionary_backend.member;

import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndpoint.ADMIN_LOGIN_URI;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndpoint.CREATE_ADMIN_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateAdminRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminGatewayManagementTests extends AbstractControllerTest {

    protected final String snippetPath = "{class-name}/{method-name}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    @DisplayName("create admin test")
    @Transactional
    @Rollback(value = false)
    public void createAdminTest() {
        var request = CreateAdminRequest.builder()
            .name("admin_test")
            .phoneNumber("01084708097")
            .birthDate("19941027")
            .gender("남")
            .loginId("admin_test")
            .password("samil123!@#")
            .email("samil@test.com")
            .build();

        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(post(CREATE_ADMIN_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        });
    }

    @Test
    @DisplayName("admin login test")
    @Transactional
    public void adminLoginTest() {
        var request = LoginUserRequest.builder()
            .loginId("admin_test")
            .password("samil123!@#")
            .build();

        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(post(ADMIN_LOGIN_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        });
    }

}
