package com.samill.missionary_backend.missionary.board.entity;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryBoardType implements EnumModel {
    NOTICE("공지사항"),
    BUS("버스"),
    ACCOMMODATION("숙소"),

    FAQ("자주 묻는 질문"),
    ;

    private final String value;

    MissionaryBoardType(String value) {
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
