package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.user.UserDTO;
import com.samill.missionaryBackend.user.UserExternalAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user/api")
public class UserGatewayManagement {

    private UserExternalAPI userExternalAPI;

    //선교 신청
    @PostMapping("/register")
    public UserDTO register() {
        return userExternalAPI.register();
    }
}