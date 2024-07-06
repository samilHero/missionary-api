package com.samill.missionary_backend.missionary.dto;

import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public record GetMissionaryGroupsQueryResult(

    @NonNull Map<MissionaryRegionType, List<GetMissionaryGroupsQueryResultMissionary>> groups
) {

}
