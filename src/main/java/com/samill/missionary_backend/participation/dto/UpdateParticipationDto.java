package com.samill.missionary_backend.participation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateParticipationDto {
    private String id;
    private String missionaryId;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
    private OffsetDateTime deletedAt;
}
