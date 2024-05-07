package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;

public record CreateMissionaryCommand(
    @NonNull
    String name,
    @NonNull
    OffsetDateTime participationStartDate,
    @NonNull
    OffsetDateTime participationEndDate,
    @NonNull
    OffsetDateTime workStartDate,
    @NonNull
    OffsetDateTime workEndDate,
    @NonNull
    Integer price,
    @NonNull
    String pastorName,
    @NonNull
    String pastorPhone,
    @NonNull
    Integer maximumParticipantCount,
    @NonNull
    String posterName,
    @NonNull
    String posterPath,
    @NonNull
    String bankAccountName,
    @NonNull
    String bankAccountPlaceHolder,
    @NonNull
    String bankAccountNumber
) {

    public List<CreateMissionaryCommandPoster> getPosters() {
        return List.of(new CreateMissionaryCommandPoster(posterName, posterPath));
    }

    public boolean isNotValidParticipationPeriod() {
        return participationEndDate.isBefore(participationStartDate) || participationEndDate.isBefore(OffsetDateTime.now());
    }

    public boolean isNotValidWorkPeriod() {
        return workEndDate.isBefore(workStartDate) || workEndDate.isBefore(OffsetDateTime.now());
    }


}
