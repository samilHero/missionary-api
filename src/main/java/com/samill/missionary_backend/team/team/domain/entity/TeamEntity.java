package com.samill.missionary_backend.team.team.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "team")
@Getter
@Setter
public class TeamEntity {

    @Id
    private String id;

    @Column
    private String missionaryId;


}
