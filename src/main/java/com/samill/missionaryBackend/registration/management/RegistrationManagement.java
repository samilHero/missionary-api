package com.samill.missionaryBackend.registration.management;

import com.samill.missionaryBackend.registration.RegistrationDTO;
import com.samill.missionaryBackend.registration.RegistrationExternalAPI;
import com.samill.missionaryBackend.registration.RegistrationInternalAPI;
import com.samill.missionaryBackend.registration.service.RegistrationService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationManagement implements RegistrationInternalAPI, RegistrationExternalAPI {

    private RegistrationService registrationService;

    @Override
    public RegistrationDTO add(RegistrationDTO registrationDTO) {
        return registrationService.add();
    }

    @Override
    public RegistrationDTO register() {
        return add(null);
    }

    @Override
    public RegistrationDTO getRegistration() {
        return registrationService.getRegistration();
    }

    @Override
    public List<RegistrationDTO> getRegistrations() {
        return registrationService.getRegistrations();
    }
}
