package com.samill.missionary_backend.missionary.missionary.enums;

import com.samill.missionary_backend.common.enums.EnumModel;

public enum MissionaryRegion implements EnumModel {
    JEJU("제주선교", MissionaryCategory.DOMESTIC),
    SEOUL("서울선교", MissionaryCategory.DOMESTIC),
    ARMY("군선교", MissionaryCategory.DOMESTIC),
    ;

    private final String value;

    private final MissionaryCategory missionaryCategory;

    MissionaryRegion(String value, MissionaryCategory missionaryCategory) {
        this.value = value;
        this.missionaryCategory = missionaryCategory;
    }


    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }

    public MissionaryCategory getMissionaryCategory() {
        return missionaryCategory;
    }

}
