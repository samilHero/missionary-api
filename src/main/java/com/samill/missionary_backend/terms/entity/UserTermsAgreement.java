package com.samill.missionary_backend.terms.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import java.time.OffsetDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user_terms_agreement SET deleted_at = current_timestamp WHERE user_terms_agreement_id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "user_terms_agreement")
public class UserTermsAgreement extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String userTermsAgreementId;

    private String userId;

    private String termsId;

    @Builder.Default
    private boolean isAgreed = Boolean.FALSE;

    private OffsetDateTime deletedAt;
}
