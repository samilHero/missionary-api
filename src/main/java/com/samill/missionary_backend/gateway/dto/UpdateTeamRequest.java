package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record UpdateTeamRequest(
        String leaderUserId,
        String teamName,
        String churchId
) {
}
