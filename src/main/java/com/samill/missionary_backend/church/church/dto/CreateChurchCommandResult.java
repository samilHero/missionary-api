package com.samill.missionary_backend.church.church.dto;

import lombok.NonNull;

public record CreateChurchCommandResult(
        @NonNull
        String churchId
) {

}
