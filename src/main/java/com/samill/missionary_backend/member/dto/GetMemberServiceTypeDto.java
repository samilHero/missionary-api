package com.samill.missionary_backend.member.dto;

import com.samill.missionary_backend.common.enums.ServiceType;

public record GetMemberServiceTypeDto(
    String memberId,
    ServiceType serviceType

) {

}
