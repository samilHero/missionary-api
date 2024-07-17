package com.samill.missionary_backend.gateway.management.admin.team;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.CREATE_TEAM;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.GET_TEAMS;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.UPDATE_TEAM;
import static com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint.UPDATE_TEAM_MEMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.epages.restdocs.apispec.Schema;
import com.samill.missionary_backend.common.AbstractControllerTestsBase;
import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.GetTeamListRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamMemberRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

public class AdminTeamGatewayManagementTests extends AbstractControllerTestsBase {

    @Test
    @DisplayName("팀 및 팀장 생성")
    void createTeam() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.post(CREATE_TEAM)
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new CreateTeamRequest(
                                "yechan1",
                                "MISSIONARY1",
                                "CHURCHID1"
                            )
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_TEAM")
                        .description("팀 및 팀장 생성 API")
                        .requestFields(
                            fieldWithPath("leaderUserId").description("팀장 UserId"),
                            fieldWithPath("missionaryId").description("선교 ID"),
                            fieldWithPath("churchId").description("교회 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }

    @Test
    @DisplayName("팀 수정")
    void updateTeam() throws Exception {
        final String teamId = UUID.randomUUID().toString();
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        UPDATE_TEAM,
                        teamId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new UpdateTeamRequest(
                                "test1",
                                "삼삼교회"
                            )
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_TEAM")
                        .description("팀 수정 API")
                        .pathParameters(parameterWithName("teamId").description("팀 ID"))
                        .requestFields(
                            fieldWithPath("leaderUserId").description("팀장 UserId"),
                            fieldWithPath("churchId").description("교회 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }

    @Test
    @DisplayName("팀 멤버 추가")
    void updateTeamMembers() throws Exception {
        final String teamId = UUID.randomUUID().toString();
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        UPDATE_TEAM_MEMBER
                        , teamId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            List.of(new UpdateTeamMemberRequest(
                                "test1"
                            ))
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_TEAM")
                        .description("팀 멤버 추가 API")
                        .pathParameters(parameterWithName("teamId").description("팀 ID"))
                        .requestFields(
                            fieldWithPath("userId").description("신청인 USER ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
            );
    }

    @Test
    @DisplayName("팀 현황")
    void getTeams() throws Exception {
        final String missionaryId = UUID.randomUUID().toString();
        mockMvc.perform(
                RestDocumentationRequestBuilders.put(
                        GET_TEAMS
                        , missionaryId
                    )
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new GetTeamListRequest(
                                "userId1",
                                "churchId1"
                            )
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("ADMIN_TEAM")
                        .description("팀 현황 목록조회 API")
                        .pathParameters(parameterWithName("missionaryId").description("선교 ID"))
                        .responseSchema(Schema.schema("GetTeamResults"))
                        .requestFields(
                            fieldWithPath("leaderUserId").description("팀장 UserId"),
                            fieldWithPath("churchId").description("교회 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                            fieldWithPath("data.content[].id").type(JsonFieldType.STRING).description("id"),
                            fieldWithPath("data.content[].missionaryId").type(JsonFieldType.STRING).description("선교ID"),
                            fieldWithPath("data.content[].churchId").type(JsonFieldType.STRING).description("교회ID"),
                            fieldWithPath("data.content[].churchName").type(JsonFieldType.STRING).description("교회이름"),
                            fieldWithPath("data.content[].leaderUserId").type(JsonFieldType.STRING).description("팀장 ID"),
                            fieldWithPath("data.content[].leaderUserName").type(JsonFieldType.STRING).description("팀장 이름"),
                            fieldWithPath("data.content[].applyCount").type(JsonFieldType.NUMBER).description("신청자수"),
                            fieldWithPath("data.content[].approvedCount").type(JsonFieldType.NUMBER).description("입금자수")
                        )
                )
            );
    }
}
