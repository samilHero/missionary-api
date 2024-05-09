package com.samill.missionary_backend.gateway.dto;

import java.util.List;
import lombok.NonNull;

public record GetUserMissionariesResult(
    @NonNull
    List<GetUserMissionariesResultMissionary> missionaries,
    String nextCursor,
    boolean hasNext
) {

}
