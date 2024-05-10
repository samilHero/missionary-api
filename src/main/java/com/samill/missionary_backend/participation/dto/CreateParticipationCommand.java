package com.samill.missionary_backend.participation.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipationCommand {
    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private String identificationNumber;
    private int applyFee;
}
