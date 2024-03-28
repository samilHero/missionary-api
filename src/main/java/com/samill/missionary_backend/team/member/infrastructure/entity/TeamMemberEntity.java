package com.samill.missionary_backend.team.member.infrastructure.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "team_member")
public class TeamMemberEntity {
    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    private TeamMemberRoleEntity role;


    @Comment("자차 운전 여부")
    @Column(name = "is_driving_self")
    private boolean isDrivingSelf;
}
