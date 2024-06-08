package com.samill.missionary_backend.missionary.dto;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetParticipationQueryResult {

    private String id;
    private String missionaryId;
    private String userId;
    private String name;
    private String memberId;
    private String birthDate;
    private Integer applyFee;
    private Boolean isPaid;
    private String identificationNumber;
    private Boolean isOwnCar;
    private OffsetDateTime createdAt;
}