package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.RestDocsSupport;
import com.samill.missionary_backend.church.ChurchExternalService;
import com.samill.missionary_backend.church.church.dto.GetChurchQueryResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "dongwook.yeom")
class AdminGatewayManagementTests extends RestDocsSupport {


    private final String baseUrl = "/api/admin";

    @MockBean
    private ChurchExternalService churchExternalService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminGatewayManagement adminGatewayManagement;

    @Override
    protected AdminGatewayManagement initController() {
        return adminGatewayManagement;
    }


    @Test
    void getChurch() throws Exception {
        final String churchId = UUID.randomUUID().toString();
        when(churchExternalService.getChurch(churchId))
                .thenReturn(
                        new GetChurchQueryResult(
                                UUID.randomUUID().toString(),
                                "삼일교회",
                                "장예찬",
                                "01012345678",
                                "삼일교회 B관"
                        )
                );

        mockMvc.perform(
                        RestDocumentationRequestBuilders.get(
                                        AdminGatewayManagement.BASE_URL + "/churches/{churchId}",
                                        churchId
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andDo(
                        document("교회 정보 조회 API",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("churchId").description("교회 고유 번호")
                                ),
                                responseFields(
                                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과 코드"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                                        fieldWithPath("data.lastChruchId").type(JsonFieldType.STRING).description("교회 고유 번호"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("교회 이름"),
                                        fieldWithPath("data.pastorName").type(JsonFieldType.STRING).description("담임 목사 이름"),
                                        fieldWithPath("data.pastorPhone").type(JsonFieldType.STRING).description("담임 목사 연락처"),
                                        fieldWithPath("data.address").type(JsonFieldType.STRING).description("교회 주소")
                                )

                        )
                )
                .andExpect(status().isOk());

    }


}
