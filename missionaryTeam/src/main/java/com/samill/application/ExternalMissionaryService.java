package com.samill.application;

public interface ExternalMissionaryService {
    Missionary getMissionary(Long missionaryId);
    boolean isMissionaryStaff(Long missionaryId, String userId);
}
