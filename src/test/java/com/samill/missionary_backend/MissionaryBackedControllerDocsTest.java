package com.samill.missionary_backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MissionaryBackedControllerDocsTest extends RestDocsSupport {

    @Override
    protected Object initController() {
        return new MissionaryBackendController();
    }

    @DisplayName("Hello World")
    @Test
    void helloWorld() throws Exception {
        // when // then
        mockMvc.perform(RestDocumentationRequestBuilders.get("/"))
            .andDo(print())
            .andExpect(status().isOk()).andDo(
                document("HelloWorld",
                    preprocessResponse(prettyPrint()),
                    queryParameters(),
                    responseBody()
                )
            );
    }

}
