package com.samill.missionary_backend.participation.dto;

import com.samill.missionary_backend.member.dto.GetUserDto;
import lombok.*;

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
    private String birthDate;
    private Integer applyFee;
    private Boolean isOwnCar;

    public void updateUserInfo(GetUserDto getUserDto) {
        this.name = getUserDto.name();
        this.birthDate = getUserDto.birthDate();
    }
}
