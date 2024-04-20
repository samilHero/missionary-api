package com.samill.missionary_backend.terms.infrastructure.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.terms.domain.eums.TermsType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import java.time.OffsetDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "terms_content")
@Where(clause = "is_deleted = false")
public class TermsContent extends BaseEntity {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String termsContentId;
    /**
     * 약관 타입
     */
    @Enumerated(EnumType.STRING)
    private TermsType termsType;
    private String termsTitle;
    private String termsContents;
    /**
     * 적용 날짜
     */
    @Column(updatable = false)
    private OffsetDateTime termsApplyAt;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;
}
