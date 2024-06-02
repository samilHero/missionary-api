package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTeamRequest(
        String leaderUserId,
        @NotNull String missionaryId,
        String teamName,
        @NotNull String churchId
) {
}
