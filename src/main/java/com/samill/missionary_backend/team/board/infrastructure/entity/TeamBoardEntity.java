package com.samill.missionary_backend.team.board.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(
        name = "team_board"
)
@Entity
public class TeamBoardEntity {

    @Id
    private String id;

    @Column
    private String teamId;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(
            mappedBy = "teamBoard"
    )
    private List<TeamBoardFileEntity> files;


}
