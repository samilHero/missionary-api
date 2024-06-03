package com.samill.missionary_backend.gateway.management;

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.samill.missionary_backend.common.AbstractControllerTest;
import com.samill.missionary_backend.gateway.dto.CreateTeamRequest;
import com.samill.missionary_backend.gateway.dto.UpdateTeamRequest;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.samill.missionary_backend.gateway.endPoint.StaffGatewayManagementEndPoint.CREATE_TEAM;
import static com.samill.missionary_backend.gateway.endPoint.StaffGatewayManagementEndPoint.UPDATE_TEAM;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StaffGatewayManagementTests extends AbstractControllerTest {

//    @Test
    @DisplayName("팀 생성")
    @Transactional
    void createTeam() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.post(CREATE_TEAM)
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new CreateTeamRequest(
                                "test1",
                                "MISSIONARY_ID",
                                "삼일교회",
                                "CHURCH_ID"
                            )
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andDo(
                document(snippetPath,
                    new ResourceSnippetParametersBuilder()
                        .tag("STAFF_TEAM")
                        .description("팀 생성 API")
                        .requestFields(
                            fieldWithPath("leaderUserId").description("팀장 UserId"),
                            fieldWithPath("missionaryId").description("선교 ID"),
                            fieldWithPath("teamName").description("팀 이름"),
                            fieldWithPath("churchId").description("교회 ID")
                        )
                        .responseFields(
                            fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                        )
                )
        );
    }

//    @Test
    @DisplayName("팀 수정")
    @Transactional
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
                                    "삼삼교회",
                                    "CHURCH_ID"
                            )
                        ))
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document(snippetPath,
                            new ResourceSnippetParametersBuilder()
                                .tag("STAFF_TEAM")
                                .description("팀 수정 API")
                                .requestFields(
                                    fieldWithPath("leaderUserId").description("팀장 UserId"),
                                    fieldWithPath("teamName").description("팀 이름"),
                                    fieldWithPath("churchId").description("교회 ID")
                                )
                                .responseFields(
                                    fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                                )
                        )
                );
    }
}
