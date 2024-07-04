package com.samill.missionary_backend.common;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class AbstractMockControllerTestsBase implements AbstractControllerTestsAuthorize {

    protected final String snippetPath = "{class-name}/{method-name}";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper jacksonObjectMapper;

    protected String getAuthorizationUserOfHeader() throws Exception {
        return this.getAuthorizationUserOfHeader(this.mockMvc, this.jacksonObjectMapper);
    }

    protected String getAuthorizationAdminOfHeader() throws Exception {
        return this.getAuthorizationAdminOfHeader(this.mockMvc, this.jacksonObjectMapper);
    }

}
