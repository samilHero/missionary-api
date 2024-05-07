package com.samill.missionary_backend.member.dto;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PutUserRequest {

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
    //약관 확인 여부
    @Builder.Default
    private Boolean isAgreeTerms = Boolean.FALSE;
    @Builder.Default
    //세례 여부
    private Boolean is_baptized = Boolean.FALSE;
    //세례 일시
    private OffsetDateTime baptizedAt;
}
