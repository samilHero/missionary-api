package com.samill.missionary_backend.missionary.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateTeamServiceCommand(
    String leaderUserId,
    @NotNull String missionaryId,
    @NotNull String churchId,
    String churchName
) {

}
