package com.samill.missionary_backend.church.dto;

import lombok.NonNull;

public record GetChurchQueryResult(
    @NonNull String id,
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull String pastorPhone,
    @NonNull String address

) {

}
