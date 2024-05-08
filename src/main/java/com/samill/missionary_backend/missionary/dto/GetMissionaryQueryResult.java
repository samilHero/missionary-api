package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetMissionaryQueryResult(

    @NonNull
    String id,
    @NonNull
    Integer maximumParticipantCount,

    @NonNull
    Integer currentParticipantCount,

    @NonNull
    OffsetDateTime participationStartDate,

    @NonNull
    OffsetDateTime participationEndDate,

    @NonNull
    OffsetDateTime workStartDate,

    @NonNull
    OffsetDateTime workEndDate,

    @NonNull
    Integer price,

    @NonNull
    String description

) {

}
