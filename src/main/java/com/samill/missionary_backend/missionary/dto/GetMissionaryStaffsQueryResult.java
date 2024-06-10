package com.samill.missionary_backend.missionary.dto;

import lombok.NonNull;

public record GetMissionaryStaffsQueryResult(
    @NonNull String missionaryId
) {

}
