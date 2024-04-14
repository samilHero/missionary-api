package com.samill.missionary_backend.member.member.enums;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum ServiceType implements EnumModel {
    ADMIN_SERVICE("어드민"),
    USER_SERVICE("유저");


    private String value;

    ServiceType(String value) {
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
