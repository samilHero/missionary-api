package com.samill.missionary_backend.gateway.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateTeamMemberRequest(
        @NotNull  String userId
) {
}
