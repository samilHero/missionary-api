package com.samill.missionaryBackend.member;

public record MemberDTO(Long id,
                        Long organizationId,
                        Long departmentId,
                        String name,
                        int age,
                        String position) {

}