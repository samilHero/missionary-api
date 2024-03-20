package com.samill.missionaryBackend.missionary.management;

import com.samill.missionaryBackend.missionary.MissionaryDTO;
import com.samill.missionaryBackend.missionary.MissionaryExternalAPI;
import com.samill.missionaryBackend.missionary.MissionaryInternalAPI;
import com.samill.missionaryBackend.missionary.service.MissionaryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class MissionaryManagement implements MissionaryInternalAPI, MissionaryExternalAPI {

    private MissionaryService missionaryService;


    @Override
    @Transactional
    public MissionaryDTO add() {
        return missionaryService.add();
    }

    @Override
    public MissionaryDTO get() {
        return missionaryService.get();
    }

    @Override
    public MissionaryDTO update() {
        return missionaryService.update();
    }

}