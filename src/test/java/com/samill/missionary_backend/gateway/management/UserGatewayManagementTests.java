package com.samill.missionary_backend.gateway.management;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.CREATE_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_MISSIONARIES;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.USER_LOGIN_URI;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.epages.restdocs.apispec.Schema;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateUserRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint;
import com.samill.missionary_backend.missionary.dto.CreateParticipationCommand;
import com.samill.missionary_backend.missionary.dto.DeleteParticipationCommand;
import com.samill.missionary_backend.missionary.dto.UpdateParticipationCommand;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


class UserGatewayManagementTests extends AbstractControllerTest {

    @Test
    @DisplayName("user sign up test")
    @Transactional
    void createUserTest() {
        var request = CreateUserRequest.builder()
            .name("test")
            .loginId("test")
            .phoneNumber("01084708097")
            .birthDate("19941027")
            .gender("남")
            .password("samil123!@#")
            .email("samil@test.com")
            .isAgreeTerms(Boolean.TRUE)
            .isBaptized(Boolean.TRUE)
            .baptizedAt(OffsetDateTime.now())
            .build();

        Assertions.assertDoesNotThrow(() -> {
            mockMvc.perform(RestDocumentationRequestBuilders.post(CREATE_USER_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(
                    document(snippetPath,
                        "선교대원 회원가입 API",
                        requestFields(
                            fieldWithPath("name").description("성명"),
                            fieldWithPath("loginId").description("로그인 id"),
                            fieldWithPath("phoneNumber").description("핸드폰 번호"),
                            fieldWithPath("birthDate").description("생년 월일"),
                            fieldWithPath("gender").description("성별"),
                            fieldWithPath("password").description("비밀 번호"),
                            fieldWithPath("email").description("이메일"),
                            fieldWithPath("isAgreeTerms").description("약관 승인 여부"),
                            fieldWithPath("isBaptized").description("세례 여부"),
                            fieldWithPath("baptizedAt").description("세례 일시")
                        ),
                        responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data").ignored()
                        )
                    )
                )
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
            mockMvc.perform(RestDocumentationRequestBuilders.post(USER_LOGIN_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(
                    document(snippetPath,
                        "선교대원 로그인 API",
                        requestFields(
                            fieldWithPath("loginId").description("로그인 id"),
                            fieldWithPath("password").description("비밀 번호")
                        ),
                        responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.token").type(JsonFieldType.STRING).description("access token")
                        )
                    )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        });
    }

    @Test
    @DisplayName("get user test")
    @Transactional
    public void getApiResponseTest() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get(GET_USER_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationUserOfHeader()))
            .andDo(print())
            .andDo(
                document(snippetPath,
                    "멤버 정보를 조회하는 API",
                    responseFields(
                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                        fieldWithPath("data.id").type(JsonFieldType.STRING).description("user 아이디"),
                        fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("로그인 id"),
                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("성명"),
                        fieldWithPath("data.birthDate").type(JsonFieldType.STRING).description("생년 월일"),
                        fieldWithPath("data.gender").type(JsonFieldType.STRING).description("성별"),
                        fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                        fieldWithPath("data.isBaptized").type(JsonFieldType.BOOLEAN).description("세례 여부"),
                        fieldWithPath("data.baptizedAt").type(JsonFieldType.STRING).description("세례 일시")
                    )
                ))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @Transactional
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

    @Test
    @Transactional
    void createParticipationTest() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.post(UserGatewayManagementEndPoint.CREATE_PARTICIPATION)
                        .content(
                            jacksonObjectMapper.writeValueAsString(
                                new CreateParticipationCommand(
                                        "71d8cee6-e2bc-472a-ab1f-c61c70dc0e51",
                                        "MEM1",
                                        "장예찬",
                                        "jang1",
                                        "932393-2929292",
                                        "19940616",
                                        30000,
                                        false
                                )
                        ))
                    .header("Authorization", getAuthorizationUserOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("USER_PARTICIPATION")
                        .description("선교 참가 신청 API")
                        .requestFields(
                            fieldWithPath("missionaryId").description("선교 ID"),
                            fieldWithPath("identificationNumber").description("주민등록번호"),
                            fieldWithPath("isOwnCar").description("자차여부")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }

    @Test
    @Transactional
    void updateParticipationTest() throws Exception {
        final String participationId = UUID.randomUUID().toString();
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        UserGatewayManagementEndPoint.UPDATE_PARTICIPATION,
                        participationId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new UpdateParticipationCommand(
                                "940616-1122221",
                                false
                            )
                        ))
                    .header("Authorization", getAuthorizationUserOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("USER_PARTICIPATION")
                        .description("선교 참가 수정 API")
                        .pathParameters(
                            RequestDocumentation.parameterWithName("participationId").description("참가신청 ID")
                        )
                        .requestFields(
                            fieldWithPath("identificationNumber").description("주민등록번호"),
                            fieldWithPath("isOwnCar").description("자차여부")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }

    @Test
    @Transactional
    void deleteParticipationTest() throws Exception {
        final String participationId = UUID.randomUUID().toString();
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        UserGatewayManagementEndPoint.DELETE_PARTICIPATION,
                        participationId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new DeleteParticipationCommand(
                                "dfdfde6-11212-121212",
                                "jang1"
                            )
                        ))
                    .header("Authorization", getAuthorizationUserOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("USER_PARTICIPATION")
                        .description("선교 참가 신청취소 API")
                        .pathParameters(
                            RequestDocumentation.parameterWithName("participationId").description("참가신청 ID")
                        )
                        .requestFields(
                            fieldWithPath("missionaryId").description("선교 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }
}
