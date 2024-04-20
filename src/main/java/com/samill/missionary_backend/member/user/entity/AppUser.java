package com.samill.missionary_backend.member.user.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_user")
public class AppUser extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String userId;
    private String loginId;
    private String name;
    private String identityNumber;
    private String phoneNumber;
    private String birthDate;
    private String gender;
    private String password;
    private String email;
    //세례 여부
    @Builder.Default
    private Boolean is_baptized = Boolean.FALSE;

    //세례 일시
    private OffsetDateTime baptizedAt;

}
