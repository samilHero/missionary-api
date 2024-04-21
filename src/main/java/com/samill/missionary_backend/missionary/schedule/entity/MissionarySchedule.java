package com.samill.missionary_backend.missionary.schedule.entity;

import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Builder
@NoArgsConstructor(
    access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@Table(name = "missionary_schedule")
@SQLDelete(sql = "UPDATE missionary_schedule SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
public class MissionarySchedule extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String title;

    private String content;

    @Getter(AccessLevel.NONE)

    @ManyToOne
    @JoinColumn(name = "missionary_id")
    private Missionary missionary;


    private OffsetDateTime deletedAt;

}
