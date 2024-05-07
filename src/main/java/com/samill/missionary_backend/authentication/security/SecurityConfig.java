package com.samill.missionary_backend.authentication.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(HttpMethod.PUT, "/api/member/user").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/member/user/token").permitAll()
                    .anyRequest().authenticated()
            )
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler))
            .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }

}

