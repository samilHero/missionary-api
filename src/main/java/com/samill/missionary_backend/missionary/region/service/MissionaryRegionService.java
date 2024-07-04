package com.samill.missionary_backend.missionary.region.service;

import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.region.dto.CreateMissionaryRegionDto;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import java.util.List;
import java.util.Map;
import lombok.NonNull;

public interface MissionaryRegionService {

    @NonNull
    MissionaryRegion createMissionaryRegion(@NonNull CreateMissionaryRegionDto createMissionaryRegionDto);

    @NonNull Map<MissionaryRegionType, List<MissionaryRegion>> getMissionaryRegionTypeMissionaryRegionsMap();
}
