package com.samill.missionary_backend.gateway.dto.Participation;

import com.samill.missionary_backend.common.dto.MemberContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipationRequest {

    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private String identificationNumber;
    private String applyFee;
    private Boolean isOwnCar;

    public void setUserInfo(MemberContext memberContext) {
        this.memberId = memberContext.getMemberId();
        this.name = memberContext.getName();
        this.userId = memberContext.getId();
    }
}
