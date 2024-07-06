package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetMissionariesByRegionQueryResultMissionary(
    @NonNull String id,
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull OffsetDateTime startDate,
    @NonNull OffsetDateTime endDate
) {

}
