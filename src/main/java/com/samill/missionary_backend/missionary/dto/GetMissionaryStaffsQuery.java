package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record GetMissionaryStaffsQuery(
    @NonNull String missionaryId
) {

}
