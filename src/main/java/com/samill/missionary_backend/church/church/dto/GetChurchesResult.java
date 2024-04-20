package com.samill.missionary_backend.church.church.dto;

import lombok.NonNull;

import java.util.List;

public record GetChurchesResult(
        @NonNull List<GetChurchesChurch> churches,
        @NonNull Boolean hasNext
) {

}


