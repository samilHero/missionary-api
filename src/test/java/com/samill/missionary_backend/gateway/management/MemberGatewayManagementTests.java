package com.samill.missionary_backend.gateway.management;

import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberGatewayManagementTests extends AbstractControllerTest {

    @Test
    @DisplayName("user sign up test")
    @Transactional
    public void putUserTest() {
        var request = CreateUserRequest.builder()
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

}
