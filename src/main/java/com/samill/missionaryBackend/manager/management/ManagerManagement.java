package com.samill.missionaryBackend.manager.management;

import com.samill.missionaryBackend.manager.ManagerDTO;
import com.samill.missionaryBackend.manager.ManagerExternalAPI;
import com.samill.missionaryBackend.manager.ManagerInternalAPI;
import com.samill.missionaryBackend.manager.service.ManagerService;
import com.samill.missionaryBackend.missionary.MissionaryInternalAPI;
import com.samill.missionaryBackend.registration.RegistrationInternalAPI;
import com.samill.missionaryBackend.staff.StaffInternalAPI;
import com.samill.missionaryBackend.user.UserInternalAPI;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor

public class ManagerManagement implements ManagerInternalAPI, ManagerExternalAPI {

    private ManagerService managerService;
    private UserInternalAPI userInternalAPI;
    private StaffInternalAPI staffInternalAPI;
    private MissionaryInternalAPI missionaryInternalAPI;
    private RegistrationInternalAPI registrationInternalAPI;

    @Override
    public ManagerDTO signUp() {
        return managerService.signUp();
    }

    @Override
    public ManagerDTO login() {
        return managerService.login();
    }

    @Override
    public ManagerDTO update() {
        return managerService.update();
    }

    //staff 선정
    @Override
    public ManagerDTO selectStaff() {
        var user = userInternalAPI.getUser();
        staffInternalAPI.save();
        return null;
    }

    //선교 등록
    @Override
    public ManagerDTO addMissionary() {
        missionaryInternalAPI.add();
        return null;
    }

    @Override
    public List<ManagerDTO> getRegistrations() {
        var missionary = missionaryInternalAPI.get();
        registrationInternalAPI.getRegistrations();
        return null;
    }

}