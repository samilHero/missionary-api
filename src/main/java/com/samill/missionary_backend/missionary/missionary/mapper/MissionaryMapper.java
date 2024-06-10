package com.samill.missionary_backend.missionary.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommandPoster;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import com.samill.missionary_backend.missionary.missionary.enums.MissionaryCategory;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
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
        @NonNull Map<MissionaryCategory, List<Missionary>> map
    ) {

        return new GetMissionaryGroupsQueryResult(
            map.entrySet()
                .stream()
                .map(
                    entry -> new AbstractMap.SimpleImmutableEntry<>(
                        entry.getKey(),
                        entry.getValue().stream().map(this::missionaryToGetMissionaryGroupsQueryResultMissionary).toList()
                    )
                )
                .collect(Collectors.toMap(SimpleImmutableEntry::getKey, SimpleImmutableEntry::getValue))
        );
    }

    @Mapping(target = "startDate", source = "period.startDate")
    @Mapping(target = "endDate", source = "period.endDate")
    GetMissionaryGroupsQueryResultMissionary missionaryToGetMissionaryGroupsQueryResultMissionary(Missionary missionary);


}

