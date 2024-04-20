package com.samill.missionary_backend.missionary.dto;

import java.util.List;

public record MissionariesDto(
    List<MissionaryDto> missionaryDtos,
    Boolean hasNext
) {

}
