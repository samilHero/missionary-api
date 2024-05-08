package com.samill.missionary_backend.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminCommand {

    @NotNull
    private String loginId;
    @NotNull
    private String password;
    @NotNull
    private String name;
    private String identityNumber;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String birthDate;
    @NotNull
    private String gender;
    private String email;
}