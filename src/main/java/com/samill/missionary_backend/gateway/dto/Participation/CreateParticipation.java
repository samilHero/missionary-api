package com.samill.missionary_backend.gateway.dto.Participation;

import com.samill.missionary_backend.common.dto.MemberContext;
import lombok.*;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateParticipation {
    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private int applyFee;
    private boolean isPaid;
    private String identificationNumber;
    private OffsetDateTime deletedAt;

    public void setUserInfo(MemberContext memberContext) {
        this.memberId = memberContext.getMemberId();
        this.name = memberContext.getName();
        this.userId = memberContext.getUserId();
    }
}