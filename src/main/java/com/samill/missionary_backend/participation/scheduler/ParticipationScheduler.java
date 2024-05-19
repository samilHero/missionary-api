package com.samill.missionary_backend.participation.scheduler;

import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.dto.GetMissionaryIdsQuery;
import com.samill.missionary_backend.participation.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ParticipationScheduler {
    private final ParticipationService participationService;
    private final MissionaryExternalService missionaryExternalService;

    // 매일 자정
//    @Scheduled(cron = "0 0 0 * * *")
    public void updateParticipationPrivacyInfo() {
        OffsetDateTime beforeDate = OffsetDateTime.now().minusDays(7);
        GetMissionaryIdsQuery getMissionaryIdsQuery = new GetMissionaryIdsQuery(beforeDate);
        List<String> list = missionaryExternalService.getDaysBeforeMissionaryIds(getMissionaryIdsQuery);
        participationService.updateParticipationPrivacyInfo(list);
    }
}
