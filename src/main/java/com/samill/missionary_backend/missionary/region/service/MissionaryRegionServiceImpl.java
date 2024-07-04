package com.samill.missionary_backend.missionary.region.service;

import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.region.dto.CreateMissionaryRegionDto;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import com.samill.missionary_backend.missionary.region.mapper.MissionaryRegionMapper;
import com.samill.missionary_backend.missionary.region.repository.MissionaryRegionRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MissionaryRegionServiceImpl implements MissionaryRegionService {

    final private MissionaryRegionRepository missionaryRegionRepository;


    public @NonNull MissionaryRegion createMissionaryRegion(@NonNull CreateMissionaryRegionDto createMissionaryRegionDto) {
        return missionaryRegionRepository.save(
            MissionaryRegionMapper.INSTANCE.createMissionaryRegionDtoToMissionaryRegion(createMissionaryRegionDto)
        );
    }

    @Override
    public @NonNull Map<MissionaryRegionType, List<MissionaryRegion>> getMissionaryRegionTypeMissionaryRegionsMap() {
        return missionaryRegionRepository.findAll().stream().collect(Collectors.groupingBy(MissionaryRegion::getType));
    }


}
