package com.samill.missionary_backend.member.dto;

public record GetUserDto(
    String id,
    String name,
    String password,
    GetMemberDto getMemberDto

) {

}
