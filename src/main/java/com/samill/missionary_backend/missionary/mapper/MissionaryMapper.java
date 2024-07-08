package com.samill.missionary_backend.missionary.mapper;

import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandResult;
import com.samill.missionary_backend.missionary.dto.AppointMissionaryStaffsCommandResultStaff;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommandPoster;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryGroupsQueryResultMissionary;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResult;
import com.samill.missionary_backend.missionary.dto.GetMissionaryRegionsQueryResultRegion;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
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
import org.springframework.data.domain.Page;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MissionaryMapper {

    MissionaryMapper INSTANCE = Mappers.getMapper(MissionaryMapper.class);


    @Mappings({
        @Mapping(target = "pastor.name", source = "pastorName"),
    })
    Missionary toMissionary(CreateMissionaryCommand updateMissionaryCommand);


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


    default @NonNull GetMissionariesByRegionQueryResult toGetMissionariesByRegionQueryResult(
        @NonNull Page<Missionary> missionaryPage
    ) {
        final Function<Missionary, GetMissionariesByRegionQueryResultMissionary> toGetMissionaryQueryResult =
            (@NonNull Missionary missionary) -> new GetMissionariesByRegionQueryResultMissionary(
                missionary.getId(),
                missionary.getName(),
                missionary.getPastor().getName(),
                missionary.getPeriod().getStartDate(),
                missionary.getPeriod().getEndDate()
            );

        return new GetMissionariesByRegionQueryResult(
            missionaryPage.getContent().stream().map(toGetMissionaryQueryResult).toList(),
            Long.valueOf(missionaryPage.getTotalElements()).intValue(),
            missionaryPage.getTotalPages() + 1,
            missionaryPage.getNumber() + 1
        );
    }


    default AppointMissionaryStaffsCommandResult toAppointMissionaryStaffsCommandResult(List<MissionaryStaff> missionaryStaff) {
        final Function<MissionaryStaff, AppointMissionaryStaffsCommandResultStaff> toAppointMissionaryStaffsCommandResultStaff =
            staff -> new AppointMissionaryStaffsCommandResultStaff(
                staff.getId(),
                staff.getUserId(),
                staff.getRole().getKey()
            );

        return new AppointMissionaryStaffsCommandResult(
            missionaryStaff.stream().map(toAppointMissionaryStaffsCommandResultStaff).toList()
        );
    }


}

