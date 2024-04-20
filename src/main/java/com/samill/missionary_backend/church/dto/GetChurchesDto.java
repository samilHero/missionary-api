package com.samill.missionary_backend.church.dto;

import java.util.List;

public record GetChurchesDto(
    List<GetChurchesChurchDto> churches,
    Boolean hasNext
) {

}


