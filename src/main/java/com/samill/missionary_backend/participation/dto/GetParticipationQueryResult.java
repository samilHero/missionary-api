package com.samill.missionary_backend.participation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.samill.missionary_backend.common.dto.MaskingType;
import com.samill.missionary_backend.common.util.MaskRequired;
import com.samill.missionary_backend.common.util.MaskingPropertySerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.OffsetDateTime;

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
    private Integer applyFee;
    private Boolean isPaid;
    private String identificationNumber;
    private Boolean isOwnCar;
    private OffsetDateTime createdAt;
}