package com.samill.missionary_backend.gateway.dto.Participation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetParticipationsResult {
    private String name;
    private String missionaryId;
    private String userId;
}
