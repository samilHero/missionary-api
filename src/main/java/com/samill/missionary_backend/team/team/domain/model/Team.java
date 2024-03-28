package com.samill.missionary_backend.team.team.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Team {

    private final String id;
    private final String name;
    private final String missionaryId;


    public String getId() {
        return this.id;
    }

    public String getMissionaryId() {
        return this.missionaryId;
    }

}
