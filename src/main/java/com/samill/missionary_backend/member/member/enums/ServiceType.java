package com.samill.missionary_backend.member.member.enums;


import com.samill.missionary_backend.common.enums.EnumModel;

public enum ServiceType implements EnumModel {
    ADMIN_SERVICE("어드민", "admin"),
    USER_SERVICE("유저", "user");


    private String value;
    private String root;

    ServiceType(String value, String root) {
        this.value = value;
        this.root = root;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getRoot() {
        return root;
    }
}
