package com.samill.missionary_backend.member.member.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.member.member.enums.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE member SET deleted_at = current_timestamp WHERE member_id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String memberId;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private OffsetDateTime deletedAt;

}
