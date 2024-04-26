package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record GetChurchResult(
        @NonNull String id,
        @NonNull String name,
        @NonNull String pastorName,
        @NonNull String pastorPhone,
        @NonNull String address


) {

}
