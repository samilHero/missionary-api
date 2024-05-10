package com.samill.missionary_backend.missionary.missionary.service;

import com.samill.missionary_backend.church.dto.CreateMissionaryCommandResult;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.dto.CreateMissionaryCommand;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.missionary.dto.UpdateMissionaryCommand;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import com.samill.missionary_backend.missionary.missionary.entity.MissionaryPoster;
import com.samill.missionary_backend.missionary.missionary.exception.InvalidParticipationPeriodException;
import com.samill.missionary_backend.missionary.missionary.exception.InvalidWorkPeriodException;
import com.samill.missionary_backend.missionary.missionary.exception.NotFoundMissionaryException;
import com.samill.missionary_backend.missionary.missionary.mapper.MissionaryMapper;
import com.samill.missionary_backend.missionary.missionary.repository.MisisonaryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryService {

    private final MisisonaryRepository missionaryRepository;

    public CreateMissionaryCommandResult createMissionary(@NonNull CreateMissionaryCommand createMissionaryCommand)
        throws CommonException {

        if (createMissionaryCommand.isNotValidParticipationPeriod()) {
            throw new InvalidParticipationPeriodException();
        }

        if (createMissionaryCommand.isNotValidWorkPeriod()) {
            throw new InvalidWorkPeriodException();
        }

        final Missionary missionary = missionaryRepository.save(
            MissionaryMapper.INSTANCE.createMissionaryCommandToMissionary(createMissionaryCommand)
        );

        return new CreateMissionaryCommandResult(missionary.getId());
    }

    public void updateMissionary(@NonNull String missionaryId, @NonNull UpdateMissionaryCommand updateMissionaryCommand) throws CommonException {
        final Missionary missionary = missionaryRepository.findById(missionaryId).orElseThrow(NotFoundMissionaryException::new);

        if (updateMissionaryCommand.isNotValidParticipationPeriod()) {
            throw new InvalidParticipationPeriodException();
        }

        if (updateMissionaryCommand.isNotValidWorkPeriod()) {
            throw new InvalidWorkPeriodException();
        }

        missionary.changeParticipationPeriod(
            Period.builder()
                .startDate(updateMissionaryCommand.participationStartDate())
                .endDate(updateMissionaryCommand.participationEndDate())
                .build()
        );

        missionary.changeWorkPeriod(
            Period.builder()
                .startDate(updateMissionaryCommand.workStartDate())
                .endDate(updateMissionaryCommand.workEndDate())
                .build()
        );

        missionary.changePrice(updateMissionaryCommand.price());
        missionary.changePastor(
            Pastor.builder()
                .name(updateMissionaryCommand.pastorName())
                .phone(updateMissionaryCommand.pastorPhone())
                .build()
        );
        missionary.changeMaximumParticipantCount(updateMissionaryCommand.maximumParticipantCount());
        missionary.changePoster(
            MissionaryPoster.builder()
                .name(updateMissionaryCommand.posterName())
                .path(updateMissionaryCommand.posterPath())
                .build()
        );

    }

    public void deleteMissionary(@NonNull String missionaryId) {
        missionaryRepository.deleteById(missionaryId);
    }

    public GetMissionaryQueryResult getMissionary(@NonNull GetMissionaryQuery getMissionaryQuery) throws CommonException {
        return MissionaryMapper.INSTANCE.missionaryToGetMissionaryQueryResult(
            missionaryRepository.findById(getMissionaryQuery.missionaryId())
                .orElseThrow(NotFoundMissionaryException::new)
        );
    }

    public void getMissionaries(String cursor) {
    }

    public boolean checkParticipate(@NonNull String missionaryId, @NonNull Integer participantCount) throws CommonException {
        final Missionary missionary = missionaryRepository.findById(missionaryId)
            .orElseThrow(NotFoundMissionaryException::new);

        return missionary.canParticipate(participantCount);
    }


}
