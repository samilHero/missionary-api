package com.samill.missionary_backend.gateway.dto;

import java.util.List;
import lombok.NonNull;

public record DisappointMissionaryStaffsResult(
    @NonNull List<AppointMissionaryStaffsRequestStaff> staffs
) {

}
