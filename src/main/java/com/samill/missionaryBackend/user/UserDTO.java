package com.samill.missionaryBackend.user;

import com.samill.missionaryBackend.missionary.MissionaryDTO;
import java.util.ArrayList;
import java.util.List;

public record UserDTO(Long id,
                      Long organizationId,
                      String name,
                      List<MissionaryDTO> employees) {

    public UserDTO(Long id, Long organizationId, String name) {
        this(id, organizationId, name, new ArrayList<>());
    }
}