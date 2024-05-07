package com.samill.missionary_backend.member.dto;

import com.samill.missionary_backend.member.member.enums.ServiceType;

public record GetMemberDto(
    String memberId,
    ServiceType serviceType

) {

}
