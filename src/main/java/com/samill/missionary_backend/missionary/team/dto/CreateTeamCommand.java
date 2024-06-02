package com.samill.missionary_backend.missionary.team.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTeamCommand(
        String leaderUserId,
        @NotNull String missionaryId,
        String teamName,
        @NotNull String churchId
) {
}
