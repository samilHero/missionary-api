package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record TeamMemberResults(
        String id,
        String userId
) {
}
