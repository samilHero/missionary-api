package com.samill.missionary_backend.participation;

import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.notification.NotificationExternalService;
import com.samill.missionary_backend.participation.infrastructure.mapper.ParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ParticipationManagement implements ParticipationExternalService {

    private final MemberExternalService memberExternalService;
    private final MissionaryExternalService missionaryExternalService;
    private final NotificationExternalService notificationExternalService;
    private final ParticipationMapper participationMapper;

}
