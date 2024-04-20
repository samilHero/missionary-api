package com.samill.missionary_backend.church.dto;

import lombok.NonNull;

public record CreateChurchDto(
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull String ministry,
    @NonNull String address
) {


}
