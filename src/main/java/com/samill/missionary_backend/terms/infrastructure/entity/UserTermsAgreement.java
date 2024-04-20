package com.samill.missionary_backend.terms.infrastructure.entity;

import com.samill.missionary_backend.common.infrastructure.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "user_terms_agreement")
@Where(clause = "is_deleted = false")
public class UserTermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String userTermsAgreementId;

    private String userId;

    private String termsId;

    @Builder.Default
    private boolean isAgreed = Boolean.FALSE;

    @Builder.Default
    private boolean isDeleted = Boolean.FALSE;
}
