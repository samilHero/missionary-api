package com.samill.missionary_backend.gateway.dto.Participation;

import com.samill.missionary_backend.common.dto.MaskingType;
import com.samill.missionary_backend.common.util.MaskRequired;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetParticipationResult {
    private String id;
    private String missionaryId;
    private String userId;
    private String name;
    private String memberId;
    private Integer applyFee;
    private Boolean isPaid;
    @MaskRequired(type = MaskingType.IDENTIFICATION_NUMBER)
    private String identificationNumber;
    private Boolean isOwnCar;
    private OffsetDateTime createdAt;
}