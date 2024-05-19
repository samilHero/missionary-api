package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.enums.ResponseCode;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.notification.NotificationExternalService;
import com.samill.missionary_backend.participation.dto.*;
import com.samill.missionary_backend.participation.service.ParticipationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ParticipationManagement implements ParticipationExternalService {
    private final ParticipationService participationService;
    private final MissionaryExternalService missionaryExternalService;
    private final NotificationExternalService notificationExternalService;

    @Override
    public void createParticipation(@NonNull CreateParticipationCommand createParticipationCommand) throws CommonException {
        validateParticipationPeriod(createParticipationCommand.getMissionaryId());
        GetMissionaryQuery getMissionaryQuery = new GetMissionaryQuery(createParticipationCommand.getMissionaryId());
        GetMissionaryQueryResult getMissionaryQueryResult = missionaryExternalService.getMissionary(getMissionaryQuery);
        int maxUserCount = getMissionaryQueryResult.maximumParticipantCount();
        createParticipationCommand.setApplyFee(getMissionaryQueryResult.price());
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
        return participationService.getParticipation(participationId);
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
}
