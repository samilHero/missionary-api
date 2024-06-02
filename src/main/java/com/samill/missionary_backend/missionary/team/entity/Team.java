package com.samill.missionary_backend.missionary.team.entity;

import com.samill.missionary_backend.missionary.dto.UpdateTeamCommand;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 선교 팀
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE team SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
public class Team {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String missionaryId;
    private String churchId;
    private String leaderUserId;
    private String teamName;
    @Builder.Default
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> teamMemberList = new ArrayList<>();
    private OffsetDateTime deletedAt;

    public void updateInfo(UpdateTeamCommand command) {
        this.churchId = command.churchId();
        this.teamName = command.teamName();
        this.leaderUserId = command.leaderUserId();
    }

    public void addTeamMember(TeamMember teamMember) {
        this.teamMemberList.add(teamMember);
        teamMember.updateTeam(this);
    }
}
