package com.samill.application;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleType {
    SYSTEM_ADMIN("시스템관리자"),
    ADMIN("교역자"),
    STAFF("준비위원"),
    USER("일반유저");

    private String name;
}
