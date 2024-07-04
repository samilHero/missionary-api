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
import com.samill.missionary_backend.gateway.endPoint.AdminGatewayManagementEndPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "dongwook.yeom")
@Transactional
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
    void 어드민_선교_지역내_선교_목록_조회() throws Exception {
        mockMvc.perform(
                RestDocumentationRequestBuilders.get(AdminGatewayManagementEndPoint.GET_MISSIONARIES)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", getAuthorizationAdminOfHeader())
                    .queryParam("region_id", "0b6a5e32-dc34-4a34-8393-0e5ce6e44b0a")
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
                                parameterWithName("region_id").type(SimpleType.STRING).description("선교 지역 ID"),
                                parameterWithName("page_number").type(SimpleType.NUMBER).description("페이지 번호").optional(),
                                parameterWithName("page_size").type(SimpleType.NUMBER).description("페이지 크기").optional()
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
