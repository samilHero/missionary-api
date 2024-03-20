package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.manager.ManagerDTO;
import com.samill.missionaryBackend.manager.ManagerExternalAPI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/manager/api")
public class ManagerGatewayManagement {

    private ManagerExternalAPI managerExternalAPI;

    @PostMapping("/signUp")
    public ManagerDTO signUp() {
        return managerExternalAPI.signUp();
    }

    @PostMapping("/update")
    public ManagerDTO update() {
        return managerExternalAPI.update();
    }

    //선교 생성
    @PostMapping("/missionary/add")
    public ManagerDTO addMissionary() {
        return managerExternalAPI.addMissionary();
    }

    //선교 스태프 설정
    @PostMapping("/staff/select")
    public ManagerDTO selectStaff() {
        return managerExternalAPI.selectStaff();
    }

    //선교 신청자 조회
    @PostMapping("/registrations")
    public List<ManagerDTO> getRegistrations() {
        return managerExternalAPI.getRegistrations();
    }

}