package com.samill.application;


import java.util.List;

public interface MissionaryStaffService {
    MissionaryStaff createStaff(String userId, Long missionaryId);
    MissionaryStaff updateStaff(String userId, Long missionaryId);
    void deleteStaff(String userId, Long missionaryId);
    List<MissionaryStaff> getStaffList();
}
