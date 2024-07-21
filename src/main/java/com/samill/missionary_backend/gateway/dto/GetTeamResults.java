package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record GetTeamResults(
    String id,
    String missionaryId,
    String churchId,
    String churchName,
    String leaderUserId,
    String leaderUserName,
    int applyCount,
    int approvedCount
) {

}
