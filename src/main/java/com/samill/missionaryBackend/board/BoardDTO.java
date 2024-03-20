package com.samill.missionaryBackend.board;

public record BoardDTO(Long id,
                       Long organizationId,
                       Long departmentId,
                       String name,
                       int age,
                       String position) {

}