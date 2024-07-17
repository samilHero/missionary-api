package com.samill.missionary_backend.missionary.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTeamCommand(
    String leaderUserId,
    @NotNull String missionaryId,
    @NotNull String churchId
) {

}
