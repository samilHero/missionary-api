package com.samill.missionary_backend.team.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTeamCommand(
        @NotNull String leaderUserId,
        @NotNull String missionaryId,
        String teamName,
        @NotNull String churchId
) {
}
