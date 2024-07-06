package com.samill.missionary_backend.missionary.enums;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryRegionType implements EnumModel {
    DOMESTIC("국내선교"),

    ABROAD("해외선교"),
    ;

    private final String value;

    MissionaryRegionType(String value) {
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
