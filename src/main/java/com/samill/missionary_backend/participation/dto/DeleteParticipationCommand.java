package com.samill.missionary_backend.participation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DeleteParticipationCommand {
    private String missionaryId;
    private String userId;
}
