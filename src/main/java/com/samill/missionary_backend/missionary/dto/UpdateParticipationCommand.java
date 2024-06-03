package com.samill.missionary_backend.missionary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateParticipationCommand {
    private String identificationNumber;
    private Boolean isOwnCar;
}
