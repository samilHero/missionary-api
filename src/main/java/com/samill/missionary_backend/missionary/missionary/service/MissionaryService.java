package com.samill.missionary_backend.missionary.missionary.service;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionariesByRegionQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryIdsQuery;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryCommand;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.exception.MissionaryException;
import com.samill.missionary_backend.missionary.mapper.MissionaryMapper;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.exception.NotFoundMissionaryException;
import com.samill.missionary_backend.missionary.missionary.repository.MissionaryRepository;
import java.time.OffsetDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryService {

    private final MissionaryRepository missionaryRepository;

    public CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand)
        throws MissionaryException {

        final Missionary missionary = missionaryRepository.save(
            MissionaryMapper.INSTANCE.createMissionaryCommandToMissionary(createMissionaryCommand)
        );

        return new CreateMissionaryCommandResult(missionary.getId());
    }

    public void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws MissionaryException {
        final var missionary = missionaryRepository.findById(missionaryId).orElseThrow(NotFoundMissionaryException::new);
//
//        if (updateMissionaryCommand.isNotValidParticipationPeriod()) {
//            throw new InvalidParticipationPeriodException();
//        }
//
//        if (updateMissionaryCommand.isNotValidWorkPeriod()) {
//            throw new InvalidWorkPeriodException();
//        }
//
//        missionary.changeParticipationPeriod(
//            Period.builder()
//                .startDate(updateMissionaryCommand.participationStartDate())
//                .endDate(updateMissionaryCommand.participationEndDate())
//                .build()
//        );
//
//        missionary.changeWorkPeriod(
//            Period.builder()
//                .startDate(updateMissionaryCommand.startDate())
//                .endDate(updateMissionaryCommand.workEndDate())
//                .build()
//        );
//
//        missionary.changePrice(updateMissionaryCommand.price());
//        missionary.changePastor(
//            Pastor.builder()
//                .name(updateMissionaryCommand.pastorName())
//                .phone(updateMissionaryCommand.pastorPhone())
//                .build()
//        );
//        missionary.changeMaximumParticipantCount(updateMissionaryCommand.maximumParticipantCount());
//        missionary.changePoster(
//            MissionaryPoster.builder()
//                .name(updateMissionaryCommand.posterName())
//                .path(updateMissionaryCommand.posterPath())
//                .build()
//        );

    }

    public void deleteMissionary(@NonNull String missionaryId) {
        missionaryRepository.deleteById(missionaryId);
    }

    public Missionary getMissionary(@NonNull String missionaryId) throws MissionaryException {
        return missionaryRepository.findById(missionaryId)
            .orElseThrow(NotFoundMissionaryException::new);

    }

    public void getMissionaries(String cursor) {
    }

    public List<String> getDaysBeforeMissionaryIds(GetMissionaryIdsQuery getMissionaryIdsQuery) {
        return missionaryRepository.findAllByDetail_ParticipationPeriod_EndDateLessThanEqual(getMissionaryIdsQuery.endDate())
            .stream()
            .map(Missionary::getId)
            .toList();
    }

    public boolean isParticipationPeriod(@NonNull String missionaryId) throws MissionaryException {
        return missionaryRepository.findById(missionaryId)
            .orElseThrow(NotFoundMissionaryException::new).isParticipationPeriod(OffsetDateTime.now());
    }


    public @NonNull Map<MissionaryRegionType, List<Missionary>> getRegionTypeMissionariesMap() {
        final var missionaries = missionaryRepository.findLatestMissionariesByRegion();

        return groupMissionariesByRegionType(missionaries);
    }

    public @NonNull Map<MissionaryRegionType, List<Missionary>> getRegionTypeMissionariesMap(@NonNull String userId) {
        final var missionaries = missionaryRepository.findLatestMissionariesByRegion(userId);

        return groupMissionariesByRegionType(missionaries);
    }

    private @NonNull Map<MissionaryRegionType, List<Missionary>> groupMissionariesByRegionType(List<Missionary> missionaries) {
        return Arrays.stream(MissionaryRegionType.values()).map(
            regionType -> new SimpleEntry<MissionaryRegionType, List<Missionary>>(
                regionType,
                missionaries.stream().filter(
                    missionary -> missionary.getSameRegionType(regionType)
                ).toList()
            )
        ).collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
    }


    public @NonNull Page<Missionary> getMissionariesByRegion(@NonNull GetMissionariesByRegionQuery getMissionariesByRegionQuery) {
        return null;
//        return missionaryRepository.findByRegion_IdOrderByPeriod_EndDateDesc(getMissionariesByRegionQuery.regionId(), );
    }
}
