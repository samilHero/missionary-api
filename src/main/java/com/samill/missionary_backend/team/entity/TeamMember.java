package com.samill.missionary_backend.team.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamMember {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private String userId;

    public void updateTeam(Team team) {
        this.team = team;
    }
}
