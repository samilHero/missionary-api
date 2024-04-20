package com.samill.missionary_backend.member.admin.infrastructure.entity;

import com.samill.missionary_backend.common.infrastructure.entity.BaseEntity;
import com.samill.missionary_backend.member.member.infrastructure.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "admin")
@Where(clause = "is_deleted = false")
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

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;
}
