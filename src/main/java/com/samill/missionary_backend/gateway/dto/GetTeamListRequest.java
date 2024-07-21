package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record GetTeamListRequest(
    String leaderUserId,
    String churchId
) {

}
