package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.enums.MissionaryStaffRole;
import lombok.NonNull;

public class AppointMissionaryStaffsCommandStaff {

    private final @NonNull String userId;

    private final @NonNull String role;

    public AppointMissionaryStaffsCommandStaff(@NonNull String userId, @NonNull String role) {
        this.userId = userId;
        this.role = role;
    }

    public String userId() {
        return userId;
    }

    public MissionaryStaffRole role() {
        return MissionaryStaffRole.valueOf(role.toUpperCase());
    }

}
