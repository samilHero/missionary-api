package com.samill.missionary_backend.gateway.dto;

public record GetUserMissionariesRequest(
    Integer pageSize,
    String cursor
) {

}
