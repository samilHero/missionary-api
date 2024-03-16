package com.samill.application;

import java.util.List;

public interface MissionaryAdminService {
    MissionaryAdmin createAdmin(String userId);
    MissionaryAdmin deleteAdmin(String userId);
    List<MissionaryAdmin> getAdminList();
}
