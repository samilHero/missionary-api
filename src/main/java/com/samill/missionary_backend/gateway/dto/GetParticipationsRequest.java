package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record GetParticipationsRequest(
        String name,
        Boolean isPaid,
        String fromDate,
        String endDate,
        int pageSize
) {
}
