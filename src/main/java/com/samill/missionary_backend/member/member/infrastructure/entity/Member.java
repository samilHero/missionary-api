package com.samill.missionary_backend.member.member.infrastructure.entity;

import com.samill.missionary_backend.common.infrastructure.entity.BaseEntity;
import com.samill.missionary_backend.member.member.domain.enums.ServiceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;


@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Where(clause = "is_deleted = false")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String memberId;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

}
