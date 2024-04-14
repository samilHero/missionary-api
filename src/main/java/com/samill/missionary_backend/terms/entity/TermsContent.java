package com.samill.missionary_backend.terms.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.terms.eums.TermsType;
import jakarta.persistence.Column;
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
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

}
