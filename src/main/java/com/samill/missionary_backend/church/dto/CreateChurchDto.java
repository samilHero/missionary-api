package com.samill.missionary_backend.church.dto;

import lombok.NonNull;

public record CreateChurchDto(
        @NonNull String name,
        @NonNull String pastorName,
        @NonNull String pastorPhone,
        @NonNull String ministry,
        @NonNull String addressBasic,
        @NonNull String addressDetail,
        @NonNull String visitPurpose
) {


}
