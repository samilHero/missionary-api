package com.samill.missionary_backend.gateway.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record GetAdminMissionariesResultMissionary(
    @NonNull String id,
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull OffsetDateTime startDate,
    @NonNull OffsetDateTime endDate
) {

}
