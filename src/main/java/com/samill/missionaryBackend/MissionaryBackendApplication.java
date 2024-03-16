package com.samill.missionaryBackend;

import com.samill.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({UserModuleConfiguration.class,MissionaryModuleConfiguration.class,
        MissionaryTeamModuleConfiguration.class, ChurchModuleConfiguration.class,
        MissionaryStaffModuleConfiguration.class, MissionaryAdminModuleConfiguration.class})
public class MissionaryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissionaryBackendApplication.class, args);

    }

}



