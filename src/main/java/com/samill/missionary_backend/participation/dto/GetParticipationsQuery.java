package com.samill.missionary_backend.participation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
public class GetParticipationsQuery {
    private String name;
    private String missionaryId;
    private String userId;
    private OffsetDateTime cursorId;
    private int pageSize;
}
