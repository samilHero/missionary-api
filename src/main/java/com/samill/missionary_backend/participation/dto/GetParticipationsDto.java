package com.samill.missionary_backend.participation.dto;

import java.util.List;

public record GetParticipationsDto (
    List<GetParticipationsParticipationDto> participations,
    Boolean hasNext
) {

    }

