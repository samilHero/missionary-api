package com.samill.missionary_backend.team;

import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.participation.ParticipationExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TeamManagement implements TeamExternalService {

    private final MissionaryExternalService missionaryExternalService;
    private final ParticipationExternalService participationExternalService;
    
}
