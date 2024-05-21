package com.samill.missionary_backend.common.event;

import lombok.NonNull;


public record UpdateParticipationEvent(
    @NonNull
    String missionaryId,
    @NonNull
    Integer count
) {


}
