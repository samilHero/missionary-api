package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record CreateMissionaryStaffsResultStaff(
    @NonNull
    String id,
    @NonNull
    String userId,
    @NonNull
    String role
) {

}
