package com.samill.missionary_backend.church.church.dto;

import lombok.NonNull;

public record CreateChurchCommand(
        @NonNull String name,
        @NonNull String pastorName,
        @NonNull String pastorPhone,
        @NonNull String addressBasic,
        @NonNull String addressDetail
) {


}
