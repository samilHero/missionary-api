package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record AppointMissionaryStaffsRequestStaff(
    @NotBlank
    @NonNull
    String userId,

    @NotBlank
    @NonNull
    String role
) {

}
