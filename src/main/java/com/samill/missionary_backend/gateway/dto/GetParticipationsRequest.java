package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record GetParticipationsRequest(
        String name,
        String userId,
        Boolean isPaid,
        int pageSize
) {
}
