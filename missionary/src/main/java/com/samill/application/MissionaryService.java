package com.samill.application;

import java.util.List;

public interface MissionaryService {
    Missionary createMissionary(Missionary missionary);
    Missionary updateMissionary(Missionary missionary);
    void deleteMissionary(Long missionaryId);
    Missionary getMissionary(Long missionaryId);
    List<Missionary> getMissionaryList();
    void insertMissionaryStaff(Long missionaryId, String userId);
    void deleteMissionaryStaff(Long missionaryId, String userId);
}
