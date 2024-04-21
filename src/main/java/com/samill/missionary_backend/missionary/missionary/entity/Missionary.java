package com.samill.missionary_backend.missionary.missionary.entity;


import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.schedule.entity.MissionarySchedule;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(
        access = AccessLevel.PROTECTED
)
@AllArgsConstructor
@SQLDelete(sql = "UPDATE missionary SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "missionary")
public class Missionary extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "participation_start_date")),
            @AttributeOverride(name = "endDate", column = @Column(name = "participation_end_date")),
    })
    private Period participationPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "work_start_date")),
            @AttributeOverride(name = "endDate", column = @Column(name = "work_end_date")),
    })
    private Period workPeriod;

    private Integer price;

    @Embedded
    private Pastor pastor;

    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "missionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionaryPoster> posters = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "missionary", orphanRemoval = true)
    private List<MissionarySchedule> schedules = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "missionary", orphanRemoval = true)
    private List<MissionaryStaff> staffs = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "missionary", orphanRemoval = true)
    private List<MissionaryBoard> boards = new ArrayList<>();

    private OffsetDateTime deletedAt;

    void addPoster(MissionaryPoster poster) {
        this.posters.add(poster);
    }
}