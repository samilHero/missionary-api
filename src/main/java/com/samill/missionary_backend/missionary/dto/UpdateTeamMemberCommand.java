package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record UpdateTeamMemberCommand(
        String userId
) {
}
