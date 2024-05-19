package com.samill.missionary_backend.participation.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateParticipationEvent {
    private String missionaryId;
    private Integer count;
}
