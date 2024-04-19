package com.samill.missionary_backend.terms.infrastructure.entity;

import com.samill.missionary_backend.common.infrastructure.entity.BaseEntity;
import com.samill.missionary_backend.terms.domain.eums.TermsType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
