package com.samill.missionary_backend.missionary.church.dto;

import lombok.NonNull;

public record GetMissionaryChurchResult(
    @NonNull String id,
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull String pastorPhone,
    @NonNull String address,
    @NonNull String visitPurpose

) {

}
