package com.samill.missionary_backend.gateway.dto;

import lombok.NonNull;

public record CreateChurchResult(
        @NonNull
        String churchId
) {
}
