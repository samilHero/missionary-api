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
@SQLDelete(sql = "UPDATE terms_content SET deleted_at = current_timestamp WHERE terms_content_id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "terms_content")
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

    private OffsetDateTime deletedAt;
}
