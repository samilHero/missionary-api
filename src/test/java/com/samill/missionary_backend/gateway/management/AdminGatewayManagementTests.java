package com.samill.missionary_backend.gateway.management;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.ADMIN_LOGIN_URI;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.CREATE_ADMIN_URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.church.dto.CreateChurchCommandResult;
import com.samill.missionary_backend.church.dto.GetChurchQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResult;
import com.samill.missionary_backend.church.dto.GetChurchesQueryResultChurch;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateAdminRequest;
import com.samill.missionary_backend.gateway.dto.CreateChurchRequest;
import com.samill.missionary_backend.gateway.dto.LoginUserRequest;
import com.samill.missionary_backend.gateway.dto.UpdateChurchRequest;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetParticipationQueryResult;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "dongwook.yeom")
class AdminGatewayManagementTests extends AbstractControllerTest {

    @MockBean
    private ChurchExternalService churchExternalService;

    @MockBean
    private MissionaryExternalService missionaryExternalService;

    @Test
    @DisplayName("create admin test")
    @Transactional
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
            mockMvc.perform(RestDocumentationRequestBuilders.post(ADMIN_LOGIN_URI)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper.writeValueAsString(request)))
                .andDo(print())
                .andDo(
                    document(snippetPath,
                        "어드민 로그인 API",
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
    void getChurchTest() throws Exception {
        final String churchId = UUID.randomUUID().toString();
        when(churchExternalService.getChurch(churchId))
            .thenReturn(
                new GetChurchQueryResult(
                    churchId,
                    "삼일교회",
                    "장예찬",
                    "01012345678",
                    "삼일교회 B관"
                )
            );

        mockMvc.perform(
                RestDocumentationRequestBuilders.get(
                        AdminGatewayManagementEndPoint.GET_CHURCH,
                        churchId
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(snippetPath,

                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_CHURCH")
                        .description("교회 상세 조회 API")
                        .pathParameters(
                            parameterWithName("churchId").description("교회 고유 번호")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.id").type(JsonFieldType.STRING).description("교회 고유 번호"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING).description("교회 이름"),
                            fieldWithPath("data.pastorName").type(JsonFieldType.STRING).description("담임 목사 이름"),
                            fieldWithPath("data.pastorPhone").type(JsonFieldType.STRING).description("담임 목사 연락처"),
                            fieldWithPath("data.address").type(JsonFieldType.STRING).description("교회 주소")
                        )

                )
            )
            .andExpect(status().isOk());

    }


    @Test
    void getChurchesTest() throws Exception {
        when(churchExternalService.getChurches())
            .thenReturn(
                new GetChurchesQueryResult(
                    List.of(
                        new GetChurchesQueryResultChurch(
                            UUID.randomUUID().toString(),
                            "삼일교회"
                        )
                    ),
                    false
                )
            );

        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_CHURCHES)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_CHURCH")
                        .description("교회 정보 목록 조회 API")
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.churches.[].id").type(JsonFieldType.STRING).description("교회 고유 번호"),
                            fieldWithPath("data.churches.[].name").type(JsonFieldType.STRING).description("교회 이름"),
                            fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부")
                        )

                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void createChurchesTest() throws Exception {
        when(churchExternalService.createChurch(any()))
            .thenReturn(
                new CreateChurchCommandResult(UUID.randomUUID().toString())
            );

        mockMvc.perform(
                RestDocumentationRequestBuilders.post(AdminGatewayManagementEndPoint.CREATE_CHURCH)
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new CreateChurchRequest(
                                "삼일교회",
                                "장예찬",
                                "01012345678",
                                "삼일교회",
                                "B관"
                            )
                        ))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_CHURCH")
                        .description("교회 생성 API")
                        .requestFields(
                            fieldWithPath("name").description("교회 이름"),
                            fieldWithPath("pastorName").description("목사 이름"),
                            fieldWithPath("pastorPhone").description("목사 연락처"),
                            fieldWithPath("addressBasic").description("교회 주소"),
                            fieldWithPath("addressDetail").description("교회 상세 주소")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.churchId").type(JsonFieldType.STRING).description("교회 고유 번호")
                        )

                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void updateChurchesTest() throws Exception {
        final String churchId = UUID.randomUUID().toString();

        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        AdminGatewayManagementEndPoint.UPDATE_CHURCH,
                        churchId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new UpdateChurchRequest(
                                "삼일교회",
                                "장예찬1",
                                "01012345678",
                                "삼일교회",
                                "B관"
                            )
                        ))
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_CHURCH")
                        .description("교회 수정 API")
                        .pathParameters(
                            parameterWithName("churchId").description("교회 고유 번호")
                        )
                        .requestFields(
                            fieldWithPath("name").description("교회 이름"),
                            fieldWithPath("pastorName").description("목사 이름"),
                            fieldWithPath("pastorPhone").description("목사 연락처"),
                            fieldWithPath("addressBasic").description("교회 주소"),
                            fieldWithPath("addressDetail").description("교회 상세 주소")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data").type(JsonFieldType.NULL).description("결과")
                        )
                )

            )
            .andExpect(status().isOk());
    }


    @Test
    void deleteChurch() throws Exception {
        final String churchId = UUID.randomUUID().toString();

        mockMvc.perform(
                RestDocumentationRequestBuilders.delete(
                        AdminGatewayManagementEndPoint.DELETE_CHURCH,
                        churchId
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(snippetPath,

                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_CHURCH")
                        .description("교회 삭제 API")
                        .pathParameters(
                            parameterWithName("churchId").description("교회 고유 번호")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data").type(JsonFieldType.NULL).description("결과")
                        )
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void getParticipationsTest() throws Exception {
        final String missionaryId = UUID.randomUUID().toString();
        when(missionaryExternalService.getParticipations(anyString(), any(), any()))
            .thenReturn(
                new PageImpl<>(
                    List.of(
                        new GetParticipationQueryResult(
                            "Participation Id",
                            missionaryId,
                            "hong1",
                            "홍길동",
                            "UUID1",
                            "19940616",
                            30000,
                            true,
                            "940626-2012345",
                            false,
                            OffsetDateTime.now()
                        ),
                        new GetParticipationQueryResult(
                            "Participation Id",
                            missionaryId,
                            "hong2",
                            "김길동",
                            "UUID1",
                            "19940616",
                            30000,
                            true,
                            "940626-2012345",
                            false,
                            OffsetDateTime.now()
                        )
                    ))
            );

        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_PARTICIPATIONS, missionaryId)
                    .param("pageSize", "50")
                    .param("pageNumber", "1")
                    .param("name", "")
                    .param("fromDate", "")
                    .param("endDate", "")
                    .param("isPaid", "")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_PARTICIPATION")
                        .description("신청자 목록 조회 API")
                        .queryParameters(
                            parameterWithName("name").description("신청자 이름"),
                            parameterWithName("isPaid").description("선교비 입금여부"),
                            parameterWithName("fromDate").description("신청일 시작"),
                            parameterWithName("fromDate").description("신청일 종료"),
                            parameterWithName("pageSize").description("조회할 페이지 단위"),
                            parameterWithName("pageNumber").description("현재 페이지")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.content[].id").type(JsonFieldType.STRING).description("신청내역 ID"),
                            fieldWithPath("data.content[].missionaryId").type(JsonFieldType.STRING).description("선교 ID"),
                            fieldWithPath("data.content[].userId").type(JsonFieldType.STRING).description("신청자 ID"),
                            fieldWithPath("data.content[].name").type(JsonFieldType.STRING).description("신청자 이름"),
                            fieldWithPath("data.content[].memberId").type(JsonFieldType.STRING).description("신청자 멤버 ID"),
                            fieldWithPath("data.content[].applyFee").type(JsonFieldType.NUMBER).description("선교입금액"),
                            fieldWithPath("data.content[].isPaid").type(JsonFieldType.BOOLEAN).description("입금여부"),
                            fieldWithPath("data.content[].identificationNumber").type(JsonFieldType.STRING).description("주민등록번호"),
                            fieldWithPath("data.content[].isOwnCar").type(JsonFieldType.BOOLEAN).description("자차여부"),
                            fieldWithPath("data.content[].createdAt").type(JsonFieldType.STRING).description("신청일시"),
                            fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수"),
                            fieldWithPath("data.size").type(JsonFieldType.NUMBER).description("총 갯수")
                        )
                )
            );
    }

    @Test
    @Transactional
    void getParticipationTest() throws Exception {
        final String participationId = UUID.randomUUID().toString();
        when(missionaryExternalService.getParticipation(participationId))
            .thenReturn(
                new GetParticipationQueryResult(
                    participationId,
                    "71d8cee6-e2bc-472a-ab1f-c61c70dc0e51",
                    "hong",
                    "홍길동",
                    "UUID-1",
                    "19940616",
                    30000,
                    true,
                    "940555-2012345",
                    false,
                    OffsetDateTime.now()
                )
            );

        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_PARTICIPATION, participationId)
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_PARTICIPATION")
                        .description("신청자 상세 조회 API")
                        .pathParameters(
                            parameterWithName("participationId").description("신청내역 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.id").type(JsonFieldType.STRING).description("신청내역 ID"),
                            fieldWithPath("data.missionaryId").type(JsonFieldType.STRING).description("선교 ID"),
                            fieldWithPath("data.userId").type(JsonFieldType.STRING).description("신청자 ID"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING).description("신청자 이름"),
                            fieldWithPath("data.memberId").type(JsonFieldType.STRING).description("신청자 멤버 ID"),
                            fieldWithPath("data.applyFee").type(JsonFieldType.NUMBER).description("선교입금액"),
                            fieldWithPath("data.isPaid").type(JsonFieldType.BOOLEAN).description("입금여부"),
                            fieldWithPath("data.identificationNumber").type(JsonFieldType.STRING).description("주민등록번호"),
                            fieldWithPath("data.isOwnCar").type(JsonFieldType.BOOLEAN).description("자차여부"),
                            fieldWithPath("data.createdAt").description("신청일시")
                        )
                )
            );
    }

    @Test
    @Transactional
    void updateParticipationPaid() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        AdminGatewayManagementEndPoint.UPDATE_PARTICIPATION_APPROVE
                    )
                    .content(
                        List.of("UUID1", "UUID2").toString()
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(snippetPath,

                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_PARTICIPATION")
                        .description("대량승인 API")
                        .requestFields(
                            fieldWithPath("ids[]").description("참가신청 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data").type(JsonFieldType.NULL).description("결과")
                        )
                )
            )
            .andExpect(status().isOk());
    }
}
