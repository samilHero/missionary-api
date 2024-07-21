package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record UpdateTeamServiceCommand(
    String leaderUserId,
    String churchId,
    String churchName) {

}
