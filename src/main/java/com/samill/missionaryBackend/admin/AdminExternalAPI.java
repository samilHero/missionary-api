package com.samill.missionaryBackend.admin;

public interface AdminExternalAPI {

    AdminDTO add(AdminDTO employee);

    AdminDTO login();

    AdminDTO update();
}