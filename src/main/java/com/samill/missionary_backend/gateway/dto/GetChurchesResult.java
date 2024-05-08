package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

import java.util.List;

public record GetChurchesResult(
        @NonNull List<GetChurchesChurch> churches,
        @NonNull Boolean hasNext
) {

}


