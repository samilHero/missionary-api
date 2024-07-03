package com.samill.missionary_backend.missionary.dto;

import java.util.List;
import lombok.NonNull;

public record DisappointMissionaryStaffsCommand(
    @NonNull
    String missionaryId,
    @NonNull
    List<String> userIds
) {

}
