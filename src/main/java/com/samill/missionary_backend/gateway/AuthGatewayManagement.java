package com.samill.missionary_backend.gateway;

import com.samill.missionary_backend.authentication.security.TokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthGatewayManagement {

    private final TokenProvider tokenProvider;

    public AuthGatewayManagement(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/test")
    public void test() {
    }

    @GetMapping("/token")
    public String getToken() {
        // 현재 인증된 사용자 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // JWT 토큰 생성
        return tokenProvider.createToken(authentication);
    }
}
