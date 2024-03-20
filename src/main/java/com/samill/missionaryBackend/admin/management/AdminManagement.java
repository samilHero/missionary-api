package com.samill.missionaryBackend.admin.management;

import com.samill.missionaryBackend.admin.AdminDTO;
import com.samill.missionaryBackend.admin.AdminExternalAPI;
import com.samill.missionaryBackend.admin.AdminInternalAPI;
import com.samill.missionaryBackend.admin.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdminManagement implements AdminInternalAPI, AdminExternalAPI {

    private AdminService adminService;


    @Override
    @Transactional
    public AdminDTO add(AdminDTO employee) {
        return null;
    }

    @Override
    public AdminDTO login() {
        return adminService.login();
    }

    @Override
    public AdminDTO update() {
        return adminService.update();
    }

}