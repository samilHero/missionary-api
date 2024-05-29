package com.samill.missionary_backend.authentication.security;

import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.token.provider.TokenProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;
    private final MemberExternalService memberManagement;

    @Override
    public void init(@NonNull HttpSecurity builder) throws Exception {
    }

    @Override
    public void configure(@NonNull HttpSecurity builder) throws Exception {
        // security 로직에 JwtFilter 등록
        builder.addFilterBefore(
            new JwtFilter(tokenProvider, memberManagement),
            UsernamePasswordAuthenticationFilter.class
        );
    }
}
