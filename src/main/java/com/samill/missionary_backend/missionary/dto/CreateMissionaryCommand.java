package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record CreateMissionaryCommand(

    @NonNull
    String regionId,
    @NonNull
    String name,
    @NonNull
    OffsetDateTime startDate,
    @NonNull
    OffsetDateTime endDate,
    @NonNull
    String pastorName

) {


}
