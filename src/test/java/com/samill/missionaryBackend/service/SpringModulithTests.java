package com.samill.missionaryBackend.service;

import com.samill.missionaryBackend.MissionaryBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.docs.Documenter.DiagramOptions;
import org.springframework.modulith.docs.Documenter.DiagramOptions.DiagramStyle;

public class SpringModulithTests {

    ApplicationModules modules = ApplicationModules.of(MissionaryBackendApplication.class);

    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        DiagramOptions.defaults()
            .withStyle(DiagramStyle.UML);
        new Documenter(modules)
            .writeModuleCanvases()
            .writeModulesAsPlantUml()
            .writeIndividualModulesAsPlantUml();
    }
}