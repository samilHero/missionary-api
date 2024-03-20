package com.samill.missionaryBackend.manager;

public record ManagerDTO(Long id,
                         Long organizationId,
                         Long departmentId,
                         String name,
                         int age,
                         String position) {

}