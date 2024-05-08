package com.samill.missionary_backend.missionary.church.dto;

import lombok.NonNull;

public record CreateMissionaryChurchRequest(
    @NonNull String name,
    @NonNull String pastorName,
    @NonNull String pastorPhone,
    @NonNull String addressBasic,
    @NonNull String addressDetail,

    @NonNull String visitPurpose
) {


}
