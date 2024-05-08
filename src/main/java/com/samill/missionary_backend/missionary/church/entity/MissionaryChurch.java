package com.samill.missionary_backend.missionary.church.entity;

import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.entity.Pastor;
import com.samill.missionary_backend.missionary.missionary.entity.Missionary;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
import lombok.NonNull;
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
@SQLDelete(sql = "UPDATE church SET deleted_at = current_timestamp WHERE id = ?")
@SQLRestriction(value = "deleted_at is NULL")
@Table(name = "church")
public class MissionaryChurch extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    private String name;

    private String visitPurpose;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "pastor_name")),
        @AttributeOverride(name = "phone", column = @Column(name = "pastor_phone"))
    })
    private Pastor pastor;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "basic", column = @Column(name = "address_basic")),
        @AttributeOverride(name = "detail", column = @Column(name = "address_detail"))

    })
    private Address address;

    @Access(AccessType.FIELD)
    @ManyToOne
    @JoinColumn(name = "missionary_id")
    private Missionary missionary;

    private OffsetDateTime deletedAt;


    public void changeName(@NonNull String name) {
        this.name = name;
    }

    public void changePastor(@NonNull Pastor pastor) {
        this.pastor = pastor;
    }


    public void changeAddress(@NonNull Address address) {
        this.address = address;
    }

    public void changeVisitPurpose(@NonNull String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

}
