package com.samill.missionary_backend.team.member.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TeamMember {

    private String id;

    private String teamId;

    private String userId;


    public boolean checkWriteableTeamBoard(String teamId) {
        return this.teamId.equals(teamId);
    }

}


