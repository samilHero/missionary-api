package com.samill.missionary_backend.missionary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipationCommand {

    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private String identificationNumber;
    private String birthDate;
    private Integer applyFee;
    private Boolean isOwnCar;

}
