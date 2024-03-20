package com.samill.missionaryBackend.manager;

import java.util.List;

public interface ManagerExternalAPI {

    ManagerDTO signUp();

    ManagerDTO login();

    ManagerDTO update();

    ManagerDTO selectStaff();

    ManagerDTO addMissionary();

    List<ManagerDTO> getRegistrations();
}