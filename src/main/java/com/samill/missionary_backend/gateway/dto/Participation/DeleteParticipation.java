package com.samill.missionary_backend.gateway.dto.Participation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DeleteParticipation {
    private String missionaryId;
    private String userId;
}
