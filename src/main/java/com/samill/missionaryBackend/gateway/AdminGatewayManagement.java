package com.samill.missionaryBackend.gateway;

import com.samill.missionaryBackend.admin.AdminDTO;
import com.samill.missionaryBackend.admin.AdminExternalAPI;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/api")
public class AdminGatewayManagement {

    private AdminExternalAPI adminExternalAPI;

    @GetMapping("/login")
    public AdminDTO login() {
        return adminExternalAPI.login();
    }

    @GetMapping("/update")
    public AdminDTO update() {
        return adminExternalAPI.update();
    }

}