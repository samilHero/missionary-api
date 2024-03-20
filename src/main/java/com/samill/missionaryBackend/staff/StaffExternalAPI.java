package com.samill.missionaryBackend.staff;


import java.util.List;

public interface StaffExternalAPI {

    StaffDTO updateMissionary();

    List<StaffDTO> getRegistrations();
}