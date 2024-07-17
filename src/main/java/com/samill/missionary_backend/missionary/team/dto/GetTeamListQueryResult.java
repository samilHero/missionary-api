package com.samill.missionary_backend.missionary.team.dto;

import lombok.Builder;

@Builder
public record GetTeamListQueryResult(
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
