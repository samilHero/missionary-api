package com.samill.missionary_backend.missionary.participation;

import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.member.dto.GetUserDto;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.*;
import com.samill.missionary_backend.missionary.participation.entity.Participation;
import com.samill.missionary_backend.missionary.participation.mapper.ParticipationMapper;
import com.samill.missionary_backend.missionary.participation.service.ParticipationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ParticipationManagement implements ParticipationExternalService {
    private final ParticipationService participationService;
    private final MissionaryExternalService missionaryExternalService;
    private final MemberExternalService memberExternalService;

    @Override
    public void createParticipation(@NonNull CreateParticipationCommand createParticipationCommand) throws Exception {
        validateParticipationPeriod(createParticipationCommand.getMissionaryId());
        int maxUserCount = getMissionaryMaxCount(createParticipationCommand.getMissionaryId());
        GetUserDto user = memberExternalService.getUserById(createParticipationCommand.getUserId());
        updateCommandWithFeeAndUserInfo(createParticipationCommand, user);
        participationService.createParticipation(createParticipationCommand, maxUserCount);
    }

    @Override
    public void deleteParticipation(String participationId, @NonNull DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        participationService.deleteParticipation(participationId, deleteParticipationCommand);
    }

    @Override
    public Page<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery, Pageable pageable) {
        return participationService.getParticipations(getParticipationsQuery, pageable);
    }

    @Override
    public GetParticipationQueryResult getParticipation(String participationId) throws CommonException {
        //user 체크 필요
        Participation participation = participationService.getParticipation(participationId);
        return ParticipationMapper.INSTANCE.entityToGetParticipationQueryResult(participation);
    }

    @Override
    public void updateParticipation(String participationId, @NonNull UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        participationService.updateParticipation(participationId, updateParticipationCommand);
    }

    private void validateParticipationPeriod(String missionaryId) throws CommonException {
        if (!missionaryExternalService.isInParticipationPeriod(missionaryId)) {
            throw new CommonException(ResponseCode.INVALID_PARTICIPATION_PERIOD);
        }
    }

    private void updateCommandWithFeeAndUserInfo(CreateParticipationCommand createParticipationCommand, GetUserDto user) throws Exception {
        createParticipationCommand.setApplyFee(getApplyFee(createParticipationCommand.getMissionaryId()));
        createParticipationCommand.updateUserInfo(user);
    }

    private int getMissionaryMaxCount(String missionaryId) throws CommonException {
        return missionaryExternalService.getMissionary(new GetMissionaryQuery(missionaryId)).maximumParticipantCount();
    }

    private int getApplyFee(String missionaryId) throws CommonException {
        return missionaryExternalService.getMissionary(new GetMissionaryQuery(missionaryId)).price();
    }
}
