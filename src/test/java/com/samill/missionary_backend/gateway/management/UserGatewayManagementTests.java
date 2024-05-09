package com.samill.missionary_backend.gateway.management;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_MISSIONARIES;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class UserGatewayManagementTests extends AbstractControllerTest {


    @Test
    @DisplayName("user sign up test")
    @Transactional
    void createUserTest() {
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

    @Test
    @DisplayName("user login test")
    @Transactional
    void userLoginTest() {
        var request = LoginUserRequest.builder()
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

    @Test
    void getMissionariesTest() throws Exception {

        mockMvc.perform(
                RestDocumentationRequestBuilders.get(GET_MISSIONARIES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, getAuthorizationUserOfHeader())
                    .queryParam("cursor", UUID.randomUUID().toString())
                    .queryParam("pageSize", "20")
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .tag("USER_MISSIONARY")
                            .description("선교 목록 조회 API")
                            .requestSchema(Schema.schema("GetUserMissionariesRequest"))
                            .responseSchema(Schema.schema("GetUserMissionariesResponse"))
                            .queryParameters(
                                parameterWithName("cursor").optional().description("커서"),
                                parameterWithName("pageSize").optional().description("목록 조회 개수")
                            )
                            .responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data.missionaries.[].id").type(JsonFieldType.STRING).description("선교 ID"),
                                fieldWithPath("data.missionaries.[].name").type(JsonFieldType.STRING).description("선교 이름"),
                                fieldWithPath("data.missionaries.[].participationStartDate").type(JsonFieldType.STRING).description("참여 시작일"),
                                fieldWithPath("data.missionaries.[].participationEndDate").type(JsonFieldType.STRING).description("참여 종료일"),
                                fieldWithPath("data.missionaries.[].thumbnailUrl").type(JsonFieldType.STRING).description("썸네일 URL"),
                                fieldWithPath("data.missionaries.[].participantCount").type(JsonFieldType.NUMBER).description("참여 인원"),
                                fieldWithPath("data.missionaries.[].maxParticipantCount").type(JsonFieldType.NUMBER).description("최대 참여 인원"),
                                fieldWithPath("data.nextCursor").type(JsonFieldType.STRING).optional().description("다음 페이지 커서"),
                                fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).optional().description("다음 페이지 존재 여부")
                            )
                            .build()
                    )
                )
            )
            .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
