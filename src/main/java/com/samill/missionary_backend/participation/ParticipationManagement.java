package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.dto.MemberContext;
import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQuery;
import com.samill.missionary_backend.missionary.dto.GetMissionaryQueryResult;
import com.samill.missionary_backend.notification.NotificationExternalService;
import com.samill.missionary_backend.participation.dto.*;
import com.samill.missionary_backend.participation.service.ParticipationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
        GetMissionaryQuery getMissionaryQuery = new GetMissionaryQuery(createParticipationCommand.getMissionaryId());
        GetMissionaryQueryResult getMissionaryQueryResult = missionaryExternalService.getMissionary(getMissionaryQuery);
        int maxUserCount = getMissionaryQueryResult.maximumParticipantCount();
        createParticipationCommand.setApplyFee(getMissionaryQueryResult.price());
        participationService.createParticipation(createParticipationCommand, maxUserCount);
    }

    @Override
    public void deleteParticipation(@NonNull DeleteParticipationCommand deleteParticipationCommand) throws CommonException {
        participationService.deleteParticipation(deleteParticipationCommand);
    }

    @Override
    public List<GetParticipationQueryResult> getParticipations(GetParticipationsQuery getParticipationsQuery) {
        return participationService.getParticipations(getParticipationsQuery);
    }

    @Override
    public GetParticipationQueryResult getParticipation(String participationId) throws CommonException {
        //user 체크 필요
        return participationService.getParticipation(participationId);
    }

    @Override
    public void updateParticipation(@NonNull UpdateParticipationCommand updateParticipationCommand) throws CommonException {
        participationService.updateParticipation(updateParticipationCommand);
    }
}
