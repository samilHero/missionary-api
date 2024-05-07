package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record GetMissionaryQuery(
    @NonNull
    String missionaryId
) {

}
