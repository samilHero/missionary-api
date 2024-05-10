package com.samill.missionary_backend.gateway.dto;

import java.time.OffsetDateTime;

public record GetUserResult(
    String id,
    String loginId,
    String name,

    String birthDate,
    String gender,
    String email,
    Boolean isBaptized,
    OffsetDateTime baptizedAt

) {

}
