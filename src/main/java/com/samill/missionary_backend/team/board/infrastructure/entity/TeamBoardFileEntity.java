package com.samill.missionary_backend.team.board.infrastructure.entity;

import jakarta.persistence.*;

@Table(
        name = "team_board_file"
)
@Entity
public class TeamBoardFileEntity {

    @Id
    private String id;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "team_board_id")
    private TeamBoardEntity teamBoard;

    @Column
    private String name;

    @Column
    private String path;


}
