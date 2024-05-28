package com.samill.missionary_backend.missionary.missionary.entity;


import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.common.entity.Period;
import com.samill.missionary_backend.missionary.board.entity.MissionaryBoard;
import com.samill.missionary_backend.missionary.staff.entity.MissionaryStaff;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    public void changeParticipationPeriod(
        Period participationPeriod
    ) {
        this.participationPeriod = participationPeriod;
    }

    public void changeWorkPeriod(
        Period workPeriod
    ) {
        this.workPeriod = workPeriod;
    }

    public void changePrice(
        Integer price
    ) {
        this.price = price;
    }

    public void changePastor(
        Pastor pastor
    ) {
        this.pastor = pastor;
    }

    public void changeDescription(
        String description
    ) {
        this.description = description;
    }

    public void changeMaximumParticipantCount(
        Integer maximumParticipantCount
    ) {
        this.maximumParticipantCount = maximumParticipantCount;
    }

    public void changeCurrentParticipantCount(
        Integer currentParticipant
    ) {
        this.currentParticipantCount = currentParticipant;
    }

    public void changePoster(MissionaryPoster poster) {
        this.posters.forEach(MissionaryPoster::unlinkMissionary);
        this.posters.clear();
        this.posters.add(poster);
    }

    public boolean canParticipate(Integer participantCount) {
        return participantCount < maximumParticipantCount && participationPeriod.inPeriod(OffsetDateTime.now());
    }

    public boolean isParticipatablePeriod(OffsetDateTime date) {
        return participationPeriod.inPeriod(date);
    }

    public boolean aaaa(OffsetDateTime date) {
        return participationPeriod.getStartDate().isAfter(date);
    }
//
//    public boolean aaaa(OffsetDateTime date) {
//        return participationPeriod.getStartDate().isAfter(date);
//    }


}