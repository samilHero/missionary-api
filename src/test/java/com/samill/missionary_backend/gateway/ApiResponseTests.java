package com.samill.missionary_backend.gateway;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samill.missionary_backend.gateway.dto.ApiResponse;
import java.util.LinkedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WithMockUser(username = "hanbyul.jung")
public class ApiResponseTests {

    protected final String snippetPath = "{class-name}/{method-name}";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Test
    @DisplayName("get user")
    public void getApiResponseTest() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.post("/api/member/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", getAuthorizationOfHeader()))
            .andDo(
                document(snippetPath,
                    "멤버 정보를 조회하는 API",
                    //아래 부터는 spring REST docs snippets 를 정의한다
                    //path parameter 에 대한 문서 정의
                    //응답에 대한 문서 정의
                    responseFields(
                        fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지"),
                        fieldWithPath("data.userId").type(JsonFieldType.STRING).description("멤버 아이디"),
                        fieldWithPath("data.username").type(JsonFieldType.STRING).description("이름")
                    )
                ))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String getAuthorizationOfHeader() throws Exception {
        var resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/auth/token")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn();

        var responseBody = resultActions.getResponse().getContentAsString();
        var apiResponse = jacksonObjectMapper.readValue(responseBody, ApiResponse.class);

        return StringUtils.join("Bearer ", ((LinkedHashMap) apiResponse.getData()).get("token"));
    }
}
