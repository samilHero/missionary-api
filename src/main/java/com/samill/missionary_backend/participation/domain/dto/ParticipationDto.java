package com.samill.missionary_backend.participation.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ParticipationDto {
    private String id;
//    private String missionaryId;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
//    private ParticipantDto participantDto;
}
