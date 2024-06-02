package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record GetTeamQueryResult(
        String id,
        String missionaryId,
        String churchId,
        String leaderUserId,
        String teamName,
        List<GetTeamMemberQueryResult> teamMemberList
) {
}
