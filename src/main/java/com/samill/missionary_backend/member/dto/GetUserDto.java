package com.samill.missionary_backend.member.dto;

import java.time.OffsetDateTime;

public record GetUserDto(
    String id,
    String loginId,
    String name,
    String password,

    String birthDate,
    String gender,
    String email,
    Boolean isBaptized,
    OffsetDateTime baptizedAt,

    GetMemberDto getMemberDto

) {

}
