package com.samill.missionary_backend.missionary.team.dto;

import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import lombok.Builder;

import java.util.List;

@Builder
public record GetTeamQueryResult(
        String id,
        String missionaryId,
        String churchId,
        String leaderUserId,
        String teamName,
        List<TeamMember> teamMemberList
) {
}
