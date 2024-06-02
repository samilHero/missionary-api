package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record GetTeamMemberQueryResult(
        String id,
        String userId
) {
}
