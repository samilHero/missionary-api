package com.samill.missionary_backend.participation.dto;

public record GetParticipationsParticipationDto (
        String id,
        String missionaryId,
        int applyFee,
        boolean isPaid,
        String identificationNumber
) {

}