package com.samill.missionary_backend.common.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordUtil {

    private final PasswordEncoder passwordEncoder;

    // 비밀번호 암호화
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    // 비밀번호 일치 여부 확인
    public boolean matches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
