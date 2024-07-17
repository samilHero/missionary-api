package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record CreateParticipationServiceCommand(
    String missionaryId,
    String memberId,
    String name,
    String userId,
    String identificationNumber,
    String birthDate,
    Integer applyFee,
    Boolean isOwnCar,
    long maxCount
) {

}
