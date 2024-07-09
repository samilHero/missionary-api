package com.samill.missionary_backend.missionary.missionary.model;

import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record MissionaryEditor(
    @NotBlank
    @NonNull
    String name,
    @NonNull
    OffsetDateTime startDate,
    @NonNull
    OffsetDateTime endDate,
    @NotBlank
    @NonNull
    String pastorName
) {

}
