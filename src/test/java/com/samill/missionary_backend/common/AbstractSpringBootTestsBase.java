package com.samill.missionary_backend.common;

import com.samill.missionary_backend.missionary.MissionaryExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public abstract class AbstractSpringBootTestsBase {

    @Autowired
    protected MissionaryExternalService missionaryExternalService;

    
}
