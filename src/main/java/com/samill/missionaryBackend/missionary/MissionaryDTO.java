package com.samill.missionaryBackend.missionary;

public record MissionaryDTO(Long id,
                            Long organizationId,
                            Long departmentId,
                            String name,
                            int age,
                            String position) {

}