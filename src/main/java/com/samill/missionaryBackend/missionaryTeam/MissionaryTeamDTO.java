package com.samill.missionaryBackend.missionaryTeam;

import com.samill.missionaryBackend.missionary.MissionaryDTO;
import com.samill.missionaryBackend.user.UserDTO;
import java.util.List;

public record MissionaryTeamDTO(Long id,
                                String name,
                                String address,
                                List<UserDTO> departments,
                                List<MissionaryDTO> employees) {

}