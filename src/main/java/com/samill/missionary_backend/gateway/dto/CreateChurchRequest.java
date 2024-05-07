package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotNull;

public record CreateChurchRequest(
    @NotNull
    String name,
    @NotNull
    String pastorName,
    @NotNull
    String pastorPhone,
    @NotNull
    String addressBasic,
    @NotNull
    String addressDetail
) {


}
