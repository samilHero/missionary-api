package com.samill.missionary_backend;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.samill.missionary_backend.team.team.domain.repository.TeamRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Disabled
@SpringBootTest
class MissionaryBackendApplicationTests {

    ApplicationModules modules = ApplicationModules.of(MissionaryBackendApplication.class);

    @Test
    void verifiesModularStructure() {
        ApplicationModules list = modules.verify();
        for (ApplicationModule applicationModule : modules) {
            System.out.println(applicationModule);
        }

        assertNotNull(list);
    }

    @Test
    void createModuleDocumentation() {
        new Documenter(modules).writeDocumentation();
    }


    @Test
    void contextLoads() {
    }


}
