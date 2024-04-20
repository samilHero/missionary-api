package com.samill.missionary_backend.missionary.staff.entity;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryStaffRole implements EnumModel {
    LEADER("준장"),
    MEMBER("팀원"),
    ;

    private final String value;

    MissionaryStaffRole(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
