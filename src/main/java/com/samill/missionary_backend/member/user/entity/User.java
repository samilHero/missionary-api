package com.samill.missionary_backend.member.user.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.member.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE app_user SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "app_user")
public class User extends BaseEntity {

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
    //세례 여부
    @Builder.Default
    private Boolean isBaptized = Boolean.FALSE;

    //세례 일시
    private OffsetDateTime baptizedAt;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    private OffsetDateTime deletedAt;

}
