package com.samill.missionary_backend.gateway.dto;

import lombok.Builder;

@Builder
public record GetParticipationsDownloadRequest(
    String name,
    Boolean isPaid,
    String fromDate,
    String endDate
) {

}
