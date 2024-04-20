package com.samill.missionary_backend.missionary.missionary.service;

import com.samill.missionary_backend.missionary.missionary.dto.CreateMissionaryDto;
import lombok.NonNull;

public interface MissionaryInternalService {

    void createMissionary(
        @NonNull String adminId,
        @NonNull CreateMissionaryDto createMissionaryDto
    );

    void updateMissionary(
        @NonNull String adminId,
        @NonNull String missionaryId
    );

    void removeMissionary(
        @NonNull String adminId,
        @NonNull String missionaryId
    );

    void getMissionary(
        @NonNull String memberId,
        @NonNull String missionaryId
    );

    void getMissionaries(
        @NonNull String memberId,
        String cursor
    );


}
