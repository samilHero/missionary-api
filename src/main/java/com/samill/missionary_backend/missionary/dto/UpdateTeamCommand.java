package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record UpdateTeamCommand (
        String leaderUserId,
        String teamName,
        String churchId) {
}
