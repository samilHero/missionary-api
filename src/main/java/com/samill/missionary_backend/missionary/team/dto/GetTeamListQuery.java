package com.samill.missionary_backend.missionary.team.dto;

import lombok.Builder;

@Builder
public record GetTeamListQuery(
    String churchId,
    String leaderUserId
) {

}
