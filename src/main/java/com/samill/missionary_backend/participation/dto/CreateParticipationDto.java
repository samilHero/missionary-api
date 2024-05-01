package com.samill.missionary_backend.participation.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipationDto {
    private String id;
    private String missionaryId;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
    private OffsetDateTime deletedAt;
}
