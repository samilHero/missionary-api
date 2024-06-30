package com.samill.missionary_backend.missionary.missionary.enums;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryCategory implements EnumModel {
    DOMESTIC("국내선교"),

    FOREIGN("해외선교"),
    ;

    private final String value;

    MissionaryCategory(String value) {
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
