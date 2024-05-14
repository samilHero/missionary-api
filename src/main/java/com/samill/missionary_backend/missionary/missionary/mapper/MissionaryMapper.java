package com.samill.missionary_backend.missionary.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommandPoster;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryMapper {

    MissionaryMapper INSTANCE = Mappers.getMapper(MissionaryMapper.class);


    @Mappings({
        @Mapping(target = "participationPeriod.startDate", source = "participationStartDate"),
        @Mapping(target = "participationPeriod.endDate", source = "participationEndDate"),
        @Mapping(target = "workPeriod.startDate", source = "workStartDate"),
        @Mapping(target = "workPeriod.endDate", source = "workEndDate"),
        @Mapping(target = "pastor.name", source = "pastorName"),
        @Mapping(target = "pastor.phone", source = "pastorPhone"),
        @Mapping(target = "posters", source = "posters", qualifiedByName = "createMissionaryCommandPosterToMissionaryPoster"),
    })
    Missionary createMissionaryCommandToMissionary(CreateMissionaryCommand updateMissionaryCommand);


    @Named("createMissionaryCommandPosterToMissionaryPoster")
    MissionaryPoster createMissionaryCommandPosterToMissionaryPoster(CreateMissionaryCommandPoster createMissionaryCommandPoster);


    GetMissionaryQueryResult missionaryToGetMissionaryQueryResult(Missionary missionary);
}

