package com.samill.missionary_backend.terms.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.terms.eums.TermsType;
import jakarta.persistence.*;
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
@SQLDelete(sql = "UPDATE terms SET deleted_at = current_timestamp WHERE terms_id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "terms")
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

    private OffsetDateTime deletedAt;

}
