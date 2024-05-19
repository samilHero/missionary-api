package com.samill.missionary_backend.gateway.dto.Participation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UpdateParticipation {
    private String identificationNumber;
    private Boolean isOwnCar;
}
