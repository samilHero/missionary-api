package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.common.exception.CommonException;
import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.MissionaryDto;
import com.samill.missionary_backend.notification.NotificationExternalService;
import com.samill.missionary_backend.participation.dto.CreateParticipationDto;
import com.samill.missionary_backend.participation.dto.GetParticipationsDto;
import com.samill.missionary_backend.participation.dto.UpdateParticipationDto;
import com.samill.missionary_backend.participation.service.ParticipationService;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ParticipationManagement implements ParticipationExternalService {

    private final MemberExternalService memberExternalService;
    private final ParticipationService participationService;
    private final MissionaryExternalService missionaryExternalService;
    private final NotificationExternalService notificationExternalService;
    private final ParticipationMapper participationMapper;

    @Override
    public void participate(@NonNull CreateParticipationDto createParticipationDto) throws CommonException {
        MissionaryDto missionaryDto = missionaryExternalService.getMissionary("111", createParticipationDto.getMissionaryId());
//        Long maxUserCount = missionaryDto.getMaxUserCount();
        int maxUserCount = 10;
        participationService.participate(createParticipationDto, maxUserCount);
    }

    @Override
    public void cancelParticipation(UpdateParticipationDto updateParticipationDto) {
        participationService.cancelParticipation(updateParticipationDto);
    }

    @Override
    public GetParticipationsDto getParticipations(String missionaryId) {
        return null;
//        return participationService.getParticipations(missionaryId);
    }
}
