package com.samill.missionary_backend.member.dto;

public record GetUserInfoDto(
    String id,
    String loginId,
    String name,

    String gender

) {

}
