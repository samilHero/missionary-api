package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record CreateChurchRequest(
        @NonNull String name,
        @NonNull String pastorName,
        @NonNull String pastorPhone,
        @NonNull String addressBasic,
        @NonNull String addressDetail
) {


}
