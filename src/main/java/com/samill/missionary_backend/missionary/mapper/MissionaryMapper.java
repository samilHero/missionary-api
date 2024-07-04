package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommandPoster;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResultRegion;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.NonNull;
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
        @Mapping(target = "pastor.name", source = "pastorName"),
        @Mapping(target = "pastor.phone", source = "pastorPhone"),
    })
    Missionary createMissionaryCommandToMissionary(CreateMissionaryCommand updateMissionaryCommand);


    @Named("createMissionaryCommandPosterToMissionaryPoster")
    MissionaryPoster createMissionaryCommandPosterToMissionaryPoster(CreateMissionaryCommandPoster createMissionaryCommandPoster);


    GetMissionaryQueryResult missionaryToGetMissionaryQueryResult(Missionary missionary);

    default GetMissionaryGroupsQueryResult categoryMissionaryMapToGetMissionaryGroupsQueryResult(
        @NonNull Map<MissionaryRegionType, List<Missionary>> map
    ) {

        return new GetMissionaryGroupsQueryResult(
            map.entrySet()
                .stream()
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(this::missionaryToGetMissionaryGroupsQueryResultMissionary).toList()
                    )
                )
        );

    }

    @Mapping(target = "startDate", source = "period.startDate")
    @Mapping(target = "endDate", source = "period.endDate")
    GetMissionaryGroupsQueryResultMissionary missionaryToGetMissionaryGroupsQueryResultMissionary(Missionary missionary);


    default @NonNull GetMissionaryRegionsQueryResult missionaryRegionTypeMissionaryRegionsToGetMissionaryRegionsQueryResult(
        @NonNull Map<MissionaryRegionType, List<MissionaryRegion>> missionaryRegionTypeMissionaryRegionsMap
    ) {

        final Function<@NonNull MissionaryRegionType, @NonNull List<GetMissionaryRegionsQueryResultRegion>> regionToResultRegion =
            (@NonNull MissionaryRegionType missionaryRegionType) -> missionaryRegionTypeMissionaryRegionsMap.get(
                    missionaryRegionType).stream()
                .map(missionaryRegion -> new GetMissionaryRegionsQueryResultRegion(missionaryRegion.getId(), missionaryRegion.getName())).toList();

        return new GetMissionaryRegionsQueryResult(
            regionToResultRegion.apply(MissionaryRegionType.DOMESTIC),
            regionToResultRegion.apply(MissionaryRegionType.ABROAD)
        );
    }

}

