package com.samill.missionary_backend.member.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record GetUsersDto(
    List<GetUserDto> getUserDtos

) {

}
