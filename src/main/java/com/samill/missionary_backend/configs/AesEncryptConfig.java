package com.samill.missionary_backend.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;

@Configuration
public class AesEncryptConfig {
    @Value("${aes.encrypt.key}")
    private String secretKey;
    @Bean
    public AesBytesEncryptor aesBytesEncryptor() {
        return new AesBytesEncryptor(secretKey, "70726506167374");
    }
}
