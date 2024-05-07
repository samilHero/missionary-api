package com.samill.missionary_backend.authentication;


import com.samill.missionary_backend.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationManagement implements AuthenticationExternalService {

    private final AuthenticationService authenticationService;

}
