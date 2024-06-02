package com.samill.missionary_backend.missionary.participation.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.util.AesEncryptConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;

/**
 * 선교 신청정보
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE participation SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
public class Participation extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String missionaryId;
    private String memberId;
    private String name;
    private String userId;
    private String birthDate;
    private Integer applyFee;
    @Builder.Default
    private Boolean isPaid = Boolean.FALSE;
    @Convert(converter = AesEncryptConverter.class)
    private String identificationNumber;
    private OffsetDateTime deletedAt;
    @Builder.Default
    private Boolean isOwnCar = Boolean.FALSE;

    public void updateIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
}
