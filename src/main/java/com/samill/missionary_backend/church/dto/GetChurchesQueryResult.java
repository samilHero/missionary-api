package com.samill.missionary_backend.church.dto;

import java.util.List;
import lombok.NonNull;

public record GetChurchesQueryResult(
    @NonNull List<GetChurchesQueryResultChurch> churches,
    @NonNull Boolean hasNext
) {

}


