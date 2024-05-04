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
    private String memberId;
    private String name;
    private String userId;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
    private OffsetDateTime deletedAt;

    public void setUserInfo(String userId, String name, String memberId) {
        this.memberId = memberId;
        this.name = name;
        this.userId = userId;
    }
}
