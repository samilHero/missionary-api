package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.OffsetDateTime;
import lombok.NonNull;

public record UpdateAdminMissionaryRequest(
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
