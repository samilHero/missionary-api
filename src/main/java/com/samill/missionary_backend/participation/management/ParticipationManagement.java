package com.samill.missionary_backend.participation.management;

import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.notification.NotificationExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import com.samill.missionary_backend.participation.ParticipationInternalService;
import com.samill.missionary_backend.participation.dto.ParticipationDto;
import com.samill.missionary_backend.participation.mapper.ParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
class ParticipationManagement implements ParticipationExternalService, ParticipationInternalService {

    private final MemberExternalService memberExternalService;
    private final MissionaryExternalService missionaryExternalService;
    private final NotificationExternalService notificationExternalService;
    private final ParticipationMapper participationMapper;

    @Override
    public void participate(ParticipationDto participationDto) {

    }

    @Override
    public void cancelParticipation(ParticipationDto participationDto) {

    }

    @Override
    public List<Pageable> getParticipationList() {
        return null;
    }

    @Override
    public void updateParticipation(ParticipationDto participationDto) {

    }

    @Override
    public void updateDeposition(ParticipationDto participationDto) {

    }
}
