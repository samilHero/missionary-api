package com.samill.missionary_backend;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;


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


}
