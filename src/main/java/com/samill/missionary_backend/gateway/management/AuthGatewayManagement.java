package com.samill.missionary_backend.gateway.management;


import com.samill.missionary_backend.authentication.AuthenticationManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthGatewayManagement {

    private final AuthenticationManagement authenticationManagement;

}
