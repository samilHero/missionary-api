package com.samill.missionary_backend.member.admin.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.member.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.time.OffsetDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE admin SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "admin")
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String loginId;
    private String name;
    private String identityNumber;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String password;
    private String email;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private OffsetDateTime deletedAt;
}
