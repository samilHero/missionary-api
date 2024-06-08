package com.samill.missionary_backend.missionary.dto;

import lombok.Builder;

@Builder
public record GetParticipationsDownloadQuery(
    String name,
    Boolean isPaid,
    String fromDate,
    String endDate
) {

}
