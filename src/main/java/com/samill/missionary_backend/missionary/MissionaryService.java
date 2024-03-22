package com.samill.missionary_backend.missionary;

import com.samill.missionary_backend.participation.ParticipationComplete;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionaryService {


    private static final Logger LOG = LoggerFactory.getLogger(MissionaryService.class);


    @ApplicationModuleListener
    public void on(ParticipationComplete event) throws InterruptedException {

        var orderId = event.missionaryId();


    }


}
