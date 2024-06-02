package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
public record GetParticipationsQuery(
        String name,
        String userId,
        Boolean isPaid,
        int pageSize
) {
}
