package com.samill.missionary_backend.missionary.staff.service;

import java.util.List;
import lombok.NonNull;

public interface MIssionaryStaffInternalService {

    void setMissionaryStaffs(
        @NonNull String adminId,
        @NonNull String missionaryId,
        @NonNull List<String> staffIds
    );
}
