package com.samill.missionary_backend.gateway;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.samill.missionary_backend.gateway.endPoint.UserGatewayManagementEndPoint.GET_USER_URI;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import com.samill.missionary_backend.common.AbstractControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureRestDocs
@WithMockUser(username = "hanbyul.jung")
public class ApiResponseTests extends AbstractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get user test")
    @Disabled
    public void getApiResponseTest() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get(GET_USER_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationUserOfHeader()))
            .andDo(
                document(snippetPath,
                    "멤버 정보를 조회하는 API",
                    //아래 부터는 spring REST docs snippets 를 정의한다
                    //path parameter 에 대한 문서 정의
                    //응답에 대한 문서 정의
                    responseFields(
                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                        fieldWithPath("data.id").type(JsonFieldType.STRING).description("user 아이디"),
                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름")
                    )/*.andWithPrefix("data.", // data 밑에 있는 필드들에 대해
                        // getMemberDto 필드를 문서화에서 제외한다.
                        subsectionWithPath("getMemberDto").ignored(),
                        subsectionWithPath("password").ignored()
                    )*/
                ))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
