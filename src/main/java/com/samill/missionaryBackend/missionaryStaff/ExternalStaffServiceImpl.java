package com.samill.missionaryBackend.missionaryStaff;

import com.samill.application.ExternalStaffService;

public class ExternalStaffServiceImpl implements ExternalStaffService {
    @Override
    public boolean isMissionaryStaff(String userId, Long missionaryId) {
        return false;
    }
}
