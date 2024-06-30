package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResultMissionary;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryMapper {

    MissionaryMapper INSTANCE = Mappers.getMapper(MissionaryMapper.class);

    GetMissionaryGroupsQueryResultMissionary missionaryToGetMissionaryGroupsQueryResultMissionary(Missionary missionary);


}
