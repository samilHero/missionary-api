package com.samill.missionary_backend.terms.infrastructure.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.terms.domain.eums.TermsType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "terms")
@Where(clause = "is_deleted = false")
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String termsId;

    @Enumerated(EnumType.STRING)
    private TermsType termsType;

    private String termsUrl;

    @Builder.Default
    private Boolean isUsed = Boolean.TRUE;

    private String termsTitle;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Builder.Default
    private Boolean isEssential = Boolean.FALSE;

    private String termsDescription;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

}
