package com.samill.missionary_backend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@Disabled
class DocumentationTests {

    ApplicationModules modules = ApplicationModules.of(MissionaryBackendApplication.class);

    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
            .writeModuleCanvases()
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }
}