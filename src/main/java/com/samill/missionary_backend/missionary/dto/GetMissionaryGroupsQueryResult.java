package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.missionary.enums.MissionaryCategory;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public record GetMissionaryGroupsQueryResult(

    @NonNull Map<MissionaryCategory, List<GetMissionaryGroupsQueryResultMissionary>> groups
) {

}
