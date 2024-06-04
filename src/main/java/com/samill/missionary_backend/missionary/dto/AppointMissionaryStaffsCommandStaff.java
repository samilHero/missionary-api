package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaffRole;
import lombok.NonNull;

public record AppointMissionaryStaffsCommandStaff(
    @NonNull
    String userId,
    MissionaryStaffRole role
) {


    @Override
    public MissionaryStaffRole role() {
        if (role == null) {
            return MissionaryStaffRole.MEMBER;
        }

        return role;
    }
}
