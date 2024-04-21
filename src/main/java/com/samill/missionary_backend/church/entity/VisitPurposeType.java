package com.samill.missionary_backend.church.entity;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum VisitPurposeType implements EnumModel {
    CHILD_CARE("어린이 사역"),
    SENIOR_CARE("어르신 사역"),

    STREET_EVANGELISM("노방 전도"),

    ETC("기타"),
    ;

    private final String value;

    VisitPurposeType(String value) {
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
