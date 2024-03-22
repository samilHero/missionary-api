package com.samill.missionary_backend.participation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipationService {


    private final @NonNull ParticipationInternalService internalService;

    private final @NonNull ApplicationEventPublisher events;

    @Transactional
    public void participate(String missionaryId) {
        internalService.participate(missionaryId);
        events.publishEvent(new ParticipationComplete(missionaryId));
    }
}
