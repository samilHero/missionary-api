package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.staff.StaffDTO;
import com.samill.missionaryBackend.staff.StaffExternalAPI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/staff/api")
public class StaffGatewayManagement {

    private StaffExternalAPI staffExternalAPI;

    //선교 수정(오픈)
    @PostMapping("/missionary/update")
    public StaffDTO updateMissionary() {
        return staffExternalAPI.updateMissionary();
    }

    //선교 신청자 조회
    @PostMapping("/registrations")
    public List<StaffDTO> getRegistrations() {
        return staffExternalAPI.getRegistrations();
    }

}