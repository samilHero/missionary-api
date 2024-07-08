package com.samill.missionary_backend.common;

import com.samill.missionary_backend.member.MemberExternalService;
import com.samill.missionary_backend.missionary.MissionaryExternalService;
import com.samill.missionary_backend.missionary.participation.repository.ParticipationRepository;
import com.samill.missionary_backend.missionary.participation.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class AbstractSpringBootTestsBase {

    @Autowired
    protected MissionaryExternalService missionaryExternalService;

    @Autowired
    protected MemberExternalService memberExternalService;


    @Autowired
    protected ParticipationService participationService;

    @Autowired
    protected ParticipationRepository participationRepository;

}
