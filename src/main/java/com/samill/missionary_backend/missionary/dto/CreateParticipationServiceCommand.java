package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.member.dto.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateParticipationServiceCommand {

    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private String identificationNumber;
    private String birthDate;
    private Integer applyFee;
    private Boolean isOwnCar;
    private long maxCount;

    public CreateParticipationServiceCommand(CreateParticipationCommand createParticipationCommand, long maxCount, Integer applyFee,
        GetUserDto getUserDto) {
        this.missionaryId = createParticipationCommand.getMissionaryId();
        this.memberId = createParticipationCommand.getMemberId();
        this.name = createParticipationCommand.getName();
        this.userId = getUserDto.name();
        this.identificationNumber = createParticipationCommand.getIdentificationNumber();
        this.birthDate = getUserDto.birthDate();
        this.applyFee = applyFee;
        this.isOwnCar = false;
        this.maxCount = maxCount;
    }
}
