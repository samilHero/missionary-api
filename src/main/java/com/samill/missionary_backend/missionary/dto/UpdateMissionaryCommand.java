package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.NonNull;

public record UpdateMissionaryCommand(
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


    public boolean isNotValidParticipationPeriod() {
        return participationEndDate.isBefore(participationStartDate) || participationEndDate.isBefore(OffsetDateTime.now());
    }

    public boolean isNotValidWorkPeriod() {
        return workEndDate.isBefore(workStartDate) || workEndDate.isBefore(OffsetDateTime.now());
    }

}
