package com.samill.missionary_backend.participation.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ParticipationDto {
    private UUID id;
    private String missionaryId;
    private String userId;
    private int applyFee;
    private boolean isPaid;
}
