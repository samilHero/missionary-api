package com.samill.missionary_backend.authentication.dto;

import com.samill.missionary_backend.member.member.enums.ServiceType;

public record GetMemberServiceTypeDto(
    ServiceType serviceType

) {

}
