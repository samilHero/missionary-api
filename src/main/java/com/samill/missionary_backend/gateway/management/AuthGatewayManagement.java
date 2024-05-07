package com.samill.missionary_backend.gateway.management;

import static com.samill.missionary_backend.gateway.endPoint.EndPoint.TOKEN_URI;

import com.samill.missionary_backend.authentication.AuthenticationManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthGatewayManagement {

    private final AuthenticationManagement authenticationManagement;

    @PostMapping(TOKEN_URI)
    public void getToken() {
    }
}
