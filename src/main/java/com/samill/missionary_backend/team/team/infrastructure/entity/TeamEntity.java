package com.samill.missionary_backend.team.team.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;


@Getter
public class TeamEntity extends AbstractAggregateRoot<TeamEntity> {

    @Id
    private String id;

    @Column(name = "missionary_id")
    private String missionaryId;

    @Column
    private String name;

    @Column(name = "church_id")
    private String churchId;


}
