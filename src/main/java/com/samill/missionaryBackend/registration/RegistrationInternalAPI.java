package com.samill.missionaryBackend.registration;

import java.util.List;

public interface RegistrationInternalAPI {

    RegistrationDTO register();

    RegistrationDTO getRegistration();

    List<RegistrationDTO> getRegistrations();
}
