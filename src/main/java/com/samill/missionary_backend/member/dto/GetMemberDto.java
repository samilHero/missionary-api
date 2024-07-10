package com.samill.missionary_backend.member.dto;

import com.samill.missionary_backend.common.enums.ServiceType;

public record GetMemberDto(
    String memberId,
    ServiceType serviceType,
    GetAdminDto adminDto,
    GetUserDto userDto

) {

}
