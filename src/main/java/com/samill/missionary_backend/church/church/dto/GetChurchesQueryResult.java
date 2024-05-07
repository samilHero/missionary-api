package com.samill.missionary_backend.church.church.dto;

import lombok.NonNull;

import java.util.List;

public record GetChurchesQueryResult(
        @NonNull List<GetChurchesQueryResultChurch> churches,
        @NonNull Boolean hasNext
) {

}


