package com.samill.missionary_backend.participation.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

/**
 * 선교 신청자 정보
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participant extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String name;
    private String gender;
    private String phone;
    private String email;
    private LocalDate birthday;
    @ManyToOne
    @JoinColumn(name = "participation_id")
    private Participation participation;
}
