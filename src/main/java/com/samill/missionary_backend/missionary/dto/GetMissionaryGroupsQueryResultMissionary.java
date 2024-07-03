package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.missionary.enums.MissionaryRegion;
import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetMissionaryGroupsQueryResultMissionary(
    @NonNull String id,

    @NonNull MissionaryRegion region,

    @NonNull String name,

    @NonNull OffsetDateTime startDate,

    @NonNull OffsetDateTime endDate
) {


}
