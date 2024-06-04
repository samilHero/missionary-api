package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record AppointMissionaryStaffsCommand(
    @NonNull
    String missionaryId,
    @NonNull
    List<AppointMissionaryStaffsCommandStaff> staffs
) {

}
