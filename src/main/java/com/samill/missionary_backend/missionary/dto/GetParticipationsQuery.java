package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
public record GetParticipationsQuery(
        String name,
        Boolean isPaid,
        String fromDate,
        String endDate,
        int pageSize
) {
}
