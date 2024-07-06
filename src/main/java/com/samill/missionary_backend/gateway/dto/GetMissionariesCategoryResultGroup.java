package com.samill.missionary_backend.gateway.dto;

import java.util.List;
import lombok.NonNull;

public record GetMissionariesCategoryResultGroup(
    @NonNull String key,
    @NonNull String name,
    @NonNull List<GetMissionariesCategoryResultMissionary> missionaries
) {

}
