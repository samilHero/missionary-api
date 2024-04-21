package com.samill.missionary_backend.missionary.staff.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Table(name = "missionary_staff", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"missionary_id", "user_id"})
})
public class MissionaryStaff extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "missionary_id")
    private Missionary missionary;


    private String userId;

    @Enumerated(value = EnumType.STRING)
    private MissionaryStaffRole role;


}
