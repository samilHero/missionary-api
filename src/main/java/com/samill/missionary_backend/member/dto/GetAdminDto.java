package com.samill.missionary_backend.member.dto;

public record GetAdminDto(
    String id,
    String name,
    String password,
    GetMemberDto getMemberDto

) {

}
