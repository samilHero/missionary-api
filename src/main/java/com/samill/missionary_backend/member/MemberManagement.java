package com.samill.missionary_backend.member;

import com.samill.missionary_backend.authentication.AuthenticationExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class MemberManagement implements MemberExternalService {

    private final AuthenticationExternalService authenticationExternalService;
}
