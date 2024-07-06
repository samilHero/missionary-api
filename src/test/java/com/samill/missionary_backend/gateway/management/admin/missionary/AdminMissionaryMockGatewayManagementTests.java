package com.samill.missionary_backend.gateway.management.admin.missionary;

import com.samill.missionary_backend.common.AbstractMockControllerTestsBase;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@WithMockUser(username = "dongwook.yeom")
@Transactional
class AdminMissionaryMockGatewayManagementTests extends AbstractMockControllerTestsBase {


}
