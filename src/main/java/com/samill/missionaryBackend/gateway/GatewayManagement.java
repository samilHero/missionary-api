package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.missionary.MissionaryExternalAPI;
import com.samill.missionaryBackend.missionaryTeam.MissionaryTeamExternalAPI;
import com.samill.missionaryBackend.user.UserExternalAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class GatewayManagement {

    private UserExternalAPI userExternalAPI;
    private MissionaryExternalAPI missionaryExternalAPI;
    private MissionaryTeamExternalAPI missionaryTeamExternalAPI;

}