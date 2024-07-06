package com.samill.missionary_backend.gateway.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetMissionariesCategoryResultMissionary(
    @NonNull String id,

    @NonNull String region,

    @NonNull String name,

    @NonNull OffsetDateTime startDate,

    @NonNull OffsetDateTime endDate
) {

}
