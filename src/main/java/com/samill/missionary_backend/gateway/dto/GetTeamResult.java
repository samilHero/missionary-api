package com.samill.missionary_backend.gateway.dto;

import com.samill.missionary_backend.missionary.team.entity.TeamMember;
import lombok.Builder;

import java.util.List;

@Builder
public record GetTeamResult(
        String id,
        String missionaryId,
        String churchId,
        String leaderUserId,
        String teamName,
        List<TeamMember> teamMemberList
) {
}
