package com.samill.missionary_backend.gateway.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetUserMissionariesResultMissionary(
    @NonNull
    String id,
    @NonNull
    String name,
    @NonNull
    OffsetDateTime participationStartDate,
    @NonNull
    OffsetDateTime participationEndDate,
    @NonNull
    String thumbnailUrl,
    @NonNull
    Integer participantCount,
    @NonNull
    Integer maxParticipantCount
) {

}

