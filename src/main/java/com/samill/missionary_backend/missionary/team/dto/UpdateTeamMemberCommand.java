package com.samill.missionary_backend.missionary.team.dto;

import lombok.Builder;

@Builder
public record UpdateTeamMemberCommand(
        String userId
) {
}
