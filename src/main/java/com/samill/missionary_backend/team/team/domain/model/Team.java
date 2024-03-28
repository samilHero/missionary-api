package com.samill.missionary_backend.team.team.domain.model;

import com.samill.missionary_backend.team.board.domain.model.TeamBoard;
import com.samill.missionary_backend.team.member.domain.model.TeamMember;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Team {

    private String id;
    private String name;
    private String missionaryId;
    private List<TeamMember> teamMembers;
    private List<TeamBoard> teamBoards;


    public String getId() {
        return this.id;
    }

    public String getMissionaryId() {
        return this.missionaryId;
    }


}
