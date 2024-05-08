package com.samill.missionary_backend.gateway.management;

import com.samill.missionary_backend.RestDocsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(MockitoExtension.class)
@WithMockUser()
class ParticipationGatewayManagementTest extends RestDocsSupport {

    @Autowired
    private ParticipationGatewayManagement participationGatewayManagement;
    @Override
    protected ParticipationGatewayManagement initController() {
        return participationGatewayManagement;
    }

    @Test
    void getParticipations() throws Exception {
        FieldDescriptor[] response = new FieldDescriptor[] {
                fieldWithPath("id").description("신청내역 ID"),
                fieldWithPath("missionaryId").description("선교 ID"),
                fieldWithPath("userId").description("신청자 ID"),
                fieldWithPath("name").description("신청자 이름"),
                fieldWithPath("memberId").description("신청자 멤버 ID"),
                fieldWithPath("applyFee").description("선교입금액"),
                fieldWithPath("paid").description("입금여부"),
                fieldWithPath("identificationNumber").description("주민등록번호"),
                fieldWithPath("createdAt").description("신청일시")
        };

        final String missionaryId = "71d8cee6-e2bc-472a-ab1f-c61c70dc0e51";
        mockMvc.perform(
                RestDocumentationRequestBuilders.get("/admin/api/participations?missionaryId={missionaryId}&pageSize=10&name=&userId=&cursorId=", missionaryId)
                        .header("Authorization", "your-token")
                )
                .andExpect(status().isOk())
                .andDo(document("participation/list",
                requestHeaders(
                        headerWithName("Authorization").description("Authorization token")
                ),
                queryParameters(
                        parameterWithName("missionaryId").description("선교 ID"),
                        parameterWithName("userId").description("신청자 ID"),
                        parameterWithName("name").description("신청자 이름"),
                        parameterWithName("cursorId").description("마지막으로 조회한 신청내역 신청일시"),
                        parameterWithName("pageSize").description("조회할 페이지 단위")
                ),
                responseFields(fieldWithPath("[]").description("An array of participation"))
                        .andWithPrefix("[].", response)
            )
        );
    }
}