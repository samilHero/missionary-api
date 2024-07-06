package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import lombok.NonNull;

public record CreateMissionaryRequest(
    @NonNull
    @NotBlank
    String missionaryRegionId,

    @NonNull
    @NotBlank
    String name,

    @NonNull
    OffsetDateTime startDate,

    @NonNull
    OffsetDateTime endDate,

    @NonNull
    @NotBlank
    String pastorName
) {

}
