package com.samill.missionaryBackend.registration;

import com.samill.missionaryBackend.missionary.MissionaryDTO;
import java.util.List;

public record RegistrationDTO(Long id,
                              Long organizationId,
                              String name,
                              List<MissionaryDTO> employees) {

}