package com.samill.missionaryBackend.staff.management;

import com.samill.missionaryBackend.missionary.MissionaryInternalAPI;
import com.samill.missionaryBackend.registration.RegistrationInternalAPI;
import com.samill.missionaryBackend.staff.StaffDTO;
import com.samill.missionaryBackend.staff.StaffExternalAPI;
import com.samill.missionaryBackend.staff.StaffInternalAPI;
import com.samill.missionaryBackend.staff.service.StaffService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class StaffManagement implements StaffInternalAPI, StaffExternalAPI {

    private StaffService staffService;
    private MissionaryInternalAPI missionaryInternalAPI;
    private RegistrationInternalAPI registrationInternalAPI;


    @Override
    public StaffDTO save() {
        return staffService.save();
    }

    @Override
    public StaffDTO updateMissionary() {
        var missionary = missionaryInternalAPI.get();
        missionaryInternalAPI.update();
        return null;
    }

    @Override
    public List<StaffDTO> getRegistrations() {
        var missionary = missionaryInternalAPI.get();
        registrationInternalAPI.getRegistrations();
        return null;
    }
}