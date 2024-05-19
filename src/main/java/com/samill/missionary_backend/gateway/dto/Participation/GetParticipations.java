package com.samill.missionary_backend.gateway.dto.Participation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class GetParticipations {
    private String name;
    private String missionaryId;
    private String userId;
}
