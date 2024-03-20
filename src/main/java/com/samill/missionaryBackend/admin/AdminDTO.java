package com.samill.missionaryBackend.admin;

public record AdminDTO(Long id,
                       Long organizationId,
                       Long departmentId,
                       String name,
                       int age,
                       String position) {

}