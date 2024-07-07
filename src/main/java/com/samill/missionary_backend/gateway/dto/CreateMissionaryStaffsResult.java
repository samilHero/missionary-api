package com.samill.missionary_backend.gateway.dto;

import java.util.List;
import lombok.NonNull;

public record CreateMissionaryStaffsResult(
    @NonNull List<CreateMissionaryStaffsResultStaff> staffs
) {

}
