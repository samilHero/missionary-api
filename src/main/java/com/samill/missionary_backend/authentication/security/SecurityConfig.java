package com.samill.missionary_backend.authentication.security;

import lombok.NonNull;
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
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(HttpMethod.GET, "/docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/health-check").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/admin/login").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/user/user").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/admin/admin").permitAll()
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

