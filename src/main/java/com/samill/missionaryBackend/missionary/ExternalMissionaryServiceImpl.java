package com.samill.missionaryBackend.missionary;

import com.samill.application.Missionary;
import com.samill.application.ExternalMissionaryService;

public class ExternalMissionaryServiceImpl implements ExternalMissionaryService {
    @Override
    public Missionary getMissionary(Long missionaryId) {
        return null;
    }

    @Override
    public boolean isMissionaryStaff(Long missionaryId, String userId) {
        return false;
    }
}
