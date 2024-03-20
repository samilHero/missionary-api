package com.samill.missionaryBackend.staff;

public record StaffDTO(Long id,
                       Long organizationId,
                       Long departmentId,
                       String name,
                       int age,
                       String position) {

}