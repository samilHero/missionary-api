package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class GetParticipationQuery {
    private String participationId;
}
