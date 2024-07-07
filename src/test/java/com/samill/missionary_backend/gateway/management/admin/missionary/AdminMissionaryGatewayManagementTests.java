package com.samill.missionary_backend.gateway.management.admin.missionary;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import com.epages.restdocs.apispec.Schema;
import com.epages.restdocs.apispec.SimpleType;
import com.samill.missionary_backend.common.AbstractControllerTestsBase;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryRequest;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryStaffsRequest;
import com.samill.missionary_backend.gateway.dto.CreateMissionaryStaffsRequestStaff;
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;

class AdminMissionaryGatewayManagementTests extends AbstractControllerTestsBase {

    @Test
    void 어드민_선교_지역_타입별_선교_지역_목록_조회() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_MISSIONARY_REGIONS)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    resource(
                        new ResourceSnippetParametersBuilder()
                            .tag("ADMIN_MISSIONARY")
                            .description("선교지역 목록 조회")
                            .responseSchema(Schema.schema("GetAdminMissionariesResponse"))
                            .responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                subsectionWithPath("data.domestic").type(JsonFieldType.ARRAY).description("국내 선교 지역 목록"),
                                fieldWithPath("data.domestic.[].id").type(JsonFieldType.STRING).description("선교 지역 키"),
                                fieldWithPath("data.domestic.[].name").type(JsonFieldType.STRING).description("선교 지역 이름"),
                                fieldWithPath("data.abroad").type(JsonFieldType.ARRAY).description("국외 선교 지역 목록"),
                                fieldWithPath("data.abroad.[].id").type(JsonFieldType.STRING).description("선교 지역 키"),
                                fieldWithPath("data.abroad.[].name").type(JsonFieldType.STRING).description("선교 지역 이름")
                            )
                            .build()
                    )
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void 어드민_선교_지역에_해당하는_선교_목록_조회() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_MISSIONARIES)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .queryParam("regionId", "0b6a5e32-dc34-4a34-8393-0e5ce6e44b0a")
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    resource(
                        new ResourceSnippetParametersBuilder()
                            .tag("ADMIN_MISSIONARY")
                            .description("선교지역 목록 조회")
                            .queryParameters(
                                parameterWithName("regionId").type(SimpleType.STRING).description("선교 지역 ID"),
                                parameterWithName("pageNumber").type(SimpleType.NUMBER).description("페이지 번호").optional().defaultValue(1),
                                parameterWithName("pageSize").type(SimpleType.NUMBER).description("페이지 크기").optional().defaultValue(10)
                            )
                            .responseSchema(Schema.schema("GetAdminMissionariesResponse"))
                            .responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                subsectionWithPath("data.missionaries").type(JsonFieldType.ARRAY).description("선교 목록"),
                                fieldWithPath("data.missionaries.[].id").type(JsonFieldType.STRING).description("선교 ID"),
                                fieldWithPath("data.missionaries.[].name").type(JsonFieldType.STRING).description("선교 이름"),
                                fieldWithPath("data.missionaries.[].pastorName").type(JsonFieldType.STRING).description("담당 목사 이름"),
                                fieldWithPath("data.missionaries.[].startDate").type(JsonFieldType.STRING).description("선교 시작 날짜"),
                                fieldWithPath("data.missionaries.[].endDate").type(JsonFieldType.STRING).description("선교 종료 날짜"),
                                fieldWithPath("data.totalCount").type(JsonFieldType.NUMBER).description("선교 목록 총 개수"),
                                fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER).description("선교 목록 총 페이지 수"),
                                fieldWithPath("data.currentPage").type(JsonFieldType.NUMBER).description("현재 페이지 번호")
                            )
                            .build()
                    )
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void 어드민_선교_생성() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.post(AdminGatewayManagementEndPoint.CREATE_MISSIONARY)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new CreateMissionaryRequest(
                                "0b6a5e32-dc34-4a34-8393-0e5ce6e44b0a",
                                "군선교 3차",
                                OffsetDateTime.now(),
                                OffsetDateTime.now().plusMonths(1),
                                "홍길동"
                            )
                        )
                    )
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    resource(
                        new ResourceSnippetParametersBuilder()
                            .tag("ADMIN_MISSIONARY")
                            .description("선교 생성")
                            .requestSchema(Schema.schema("CreateMissionaryRequest"))
                            .requestFields(
                                fieldWithPath("missionaryRegionId").type(JsonFieldType.STRING).description("선교 지역 ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("선교 이름"),
                                fieldWithPath("startDate").type(JsonFieldType.STRING).description("선교 시작 날짜"),
                                fieldWithPath("endDate").type(JsonFieldType.STRING).description("선교 종료 날짜"),
                                fieldWithPath("pastorName").type(JsonFieldType.STRING).description("담당 목사 이름")
                            )
                            .responseSchema(Schema.schema("CreateMissionaryResponse"))
                            .responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                fieldWithPath("data.id").type(JsonFieldType.STRING).description("생성된 선교 ID")
                            )
                            .build()
                    )
                )
            )
            .andExpect(status().isOk());
    }

    @Test
    void 선교_준비팀_임명() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.post(
                        AdminGatewayManagementEndPoint.CREATE_MISSIONARY_STAFF,
                        "db6d5fb0-2b68-47cd-ba6d-653276c23efb"
                    )
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .queryParam("missionaryId", "db6d5fb0-2b68-47cd-ba6d-653276c23efb")
                    .content(
                        jacksonObjectMapper.writeValueAsString(
                            new CreateMissionaryStaffsRequest(
                                List.of(
                                    new CreateMissionaryStaffsRequestStaff(
                                        "8ff1051f-085c-42ee-bacc-06656c9bf8db",
                                        "MEMBER"
                                    )
                                )
                            )
                        )
                    )
            )
            .andDo(print())
            .andDo(
                document(
                    snippetPath,
                    resource(
                        new ResourceSnippetParametersBuilder()
                            .tag("ADMIN_MISSIONARY")
                            .description("선교 준비팀 임명")
                            .requestSchema(Schema.schema("CreateMissionaryStaffsRequest"))
                            .requestFields(
                                subsectionWithPath("staffs").type(JsonFieldType.ARRAY).description("선교 준비팀 목록"),
                                fieldWithPath("staffs.[].userId").type(JsonFieldType.STRING).description("사용자 ID"),
                                fieldWithPath("staffs.[].role").type(JsonFieldType.STRING).description("역할 (MEMBER, LEADER)")
                            )
                            .responseSchema(Schema.schema("CreateMissionaryStaffsResponse"))
                            .responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                subsectionWithPath("data.staffs").type(JsonFieldType.ARRAY).description("선교 준비팀 목록"),
                                fieldWithPath("data.staffs.[].id").type(JsonFieldType.STRING).description("준비팀 ID"),
                                fieldWithPath("data.staffs.[].userId").type(JsonFieldType.STRING).description("사용자 ID"),
                                fieldWithPath("data.staffs.[].role").type(JsonFieldType.STRING).description("역할 (MEMBER, LEADER)")
                            )
                            .build()
                    )
                )
            )
            .andExpect(status().isOk());

    }
//    @Test
//    @Transactional
//    void 어드민_카테고리별_선교_조회() throws Exception {
//
//        mockMvc.perform(
//                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_MISSIONARIES_CATEGORY)
//                    .accept(MediaType.APPLICATION_JSON)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .header("Authorization", getAuthorizationAdminOfHeader())
//            )
//            .andDo(print())
//            .andDo(
//                document(
//                    snippetPath,
//                    resource(
//                        new ResourceSnippetParametersBuilder()
//                            .tag("ADMIN_MISSIONARY")
//                            .description("카테고리별 선교 목록 조회")
//                            .responseSchema(Schema.schema("GetAdminMissionariesResponse"))
//                            .responseFields(
//                                this.generateResponseFields(
//                                    new ResponseField("data.domestic", null, "국내 선교 목록", true),
//                                    new ResponseField("data.domestic.key", JsonFieldType.STRING, "선교 카테고리 키", false),
//                                    new ResponseField("data.domestic.name", JsonFieldType.STRING, "선교 카테고리 이름", false),
//                                    new ResponseField("data.domestic.missionaries", JsonFieldType.ARRAY, "선교 목록", false),
//                                    new ResponseField("data.domestic.missionaries.[].id", JsonFieldType.STRING, "선교 ID", false),
//                                    new ResponseField("data.domestic.missionaries.[].region", JsonFieldType.STRING, "선교 지역", false),
//                                    new ResponseField("data.domestic.missionaries.[].name", JsonFieldType.STRING, "선교 이름", false),
//                                    new ResponseField("data.domestic.missionaries.[].startDate", JsonFieldType.STRING, "선교 시작 기간", false),
//                                    new ResponseField("data.domestic.missionaries.[].endDate", JsonFieldType.STRING, "선교 종료 기간", false),
//                                    new ResponseField("data.abroad", null, "해외 선교 목록", true),
//                                    new ResponseField("data.abroad.key", JsonFieldType.STRING, "선교 카테고리 키", false),
//                                    new ResponseField("data.abroad.name", JsonFieldType.STRING, "선교 카테고리 이름", false),
//                                    new ResponseField("data.abroad.missionaries", JsonFieldType.ARRAY, "선교 목록", false),
//                                    new ResponseField("data.abroad.missionaries.[].id", JsonFieldType.STRING, "선교 ID", false),
//                                    new ResponseField("data.abroad.missionaries.[].region", JsonFieldType.STRING, "선교 지역", false),
//                                    new ResponseField("data.abroad.missionaries.[].name", JsonFieldType.STRING, "선교 이름", false),
//                                    new ResponseField("data.abroad.missionaries.[].startDate", JsonFieldType.STRING, "선교 시작 기간", false),
//                                    new ResponseField("data.abroad.missionaries.[].endDate", JsonFieldType.STRING, "선교 종료 기간", false)
//                                )
//                            ).build()
//                    )
//
//                )
//            )
//            .andExpect(status().isOk());
//    }
}
