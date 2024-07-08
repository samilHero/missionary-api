package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record AppointMissionaryStaffsCommandResultStaff(
    @NonNull
    String id,
    @NonNull
    String userId,
    @NonNull
    String role
) {


}
