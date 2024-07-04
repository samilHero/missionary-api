package com.samill.missionary_backend.gateway.dto;

import java.util.List;
import lombok.NonNull;

public record GetAdminMissionariesResult(
    @NonNull List<GetAdminMissionariesResultMissionary> missionaries,
    int totalCount,
    int totalPages,
    int currentPage
) {

}
