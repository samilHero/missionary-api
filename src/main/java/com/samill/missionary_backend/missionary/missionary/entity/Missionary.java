package com.samill.missionary_backend.missionary.missionary.entity;


import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.missionary.enums.MissionaryRegionType;
import com.samill.missionary_backend.missionary.region.entity.MissionaryRegion;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@SQLDelete(sql = "UPDATE missionary SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "missionary")
public class Missionary extends BaseEntity {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;

    @Embedded
    private Period period;


    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "missionary_region_id")
    private MissionaryRegion region;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "pastor_name")),
        @AttributeOverride(name = "phoneNumber", column = @Column(name = "pastor_phone_number")),
    })
    private Pastor pastor;

    @Embedded
    private MissionaryDetail detail;

    @Builder.Default
    @OneToMany(mappedBy = "missionary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MissionaryPoster> posters = new ArrayList<>();
    private OffsetDateTime deletedAt;

    @Builder.Default
    @OneToMany(mappedBy = "missionary")
    private List<MissionaryStaff> missionaryStaffs = new ArrayList<>();

    public boolean isParticipationPeriod(OffsetDateTime date) {
        return detail.isParticipationPeriod(date);
    }

    @Override
    public String toString() {
        return "Missionary{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", period=" + period +
            ", pastor=" + pastor +
            ", detail=" + detail +
            ", deletedAt=" + deletedAt +
            '}';
    }

    public boolean getSameRegionType(MissionaryRegionType missionaryRegionType) {
        return region.getSameType(missionaryRegionType);
    }
}