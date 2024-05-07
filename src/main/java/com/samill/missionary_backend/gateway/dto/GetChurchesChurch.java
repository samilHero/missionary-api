package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record GetChurchesChurch(
        @NonNull String id,
        @NonNull String name

) {


}
