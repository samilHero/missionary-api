package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdminRequest {

    @NotNull
    private String loginId;
    @NotNull
    private String password;
}
