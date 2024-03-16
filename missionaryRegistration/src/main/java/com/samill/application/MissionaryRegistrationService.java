package com.samill.application;

import java.util.List;

public interface MissionaryRegistrationService {
    MissionaryRegistration register(String userId, Long missionaryId);
    void cancelRegistration(String userId, Long missionaryId);
    List<MissionaryRegistration> getRegisterationList(Long missionaryId);

}
