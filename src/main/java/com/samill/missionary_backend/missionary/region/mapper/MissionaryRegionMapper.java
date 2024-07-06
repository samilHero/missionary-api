package com.samill.missionary_backend.missionary.region.mapper;

import com.samill.missionary_backend.missionary.region.dto.CreateMissionaryRegionDto;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface MissionaryRegionMapper {

    MissionaryRegionMapper INSTANCE = Mappers.getMapper(MissionaryRegionMapper.class);

    MissionaryRegion createMissionaryRegionDtoToMissionaryRegion(CreateMissionaryRegionDto createMissionaryRegionDto);
}
