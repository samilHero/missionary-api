package com.samill.missionary_backend.missionary.missionary.entity;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryRegion implements EnumModel {
    JEJU("제주선교"),
    SEOUL("서울선교"),
    ;

    private final String value;

    MissionaryRegion(String value) {
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
