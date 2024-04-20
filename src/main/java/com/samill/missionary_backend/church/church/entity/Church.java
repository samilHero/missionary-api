package com.samill.missionary_backend.church.church.entity;

import com.samill.missionary_backend.church.church.dto.UpdateChurchRequest;
import com.samill.missionary_backend.common.entity.Address;
import com.samill.missionary_backend.common.entity.BaseEntity;
import com.samill.missionary_backend.common.entity.Pastor;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.OffsetDateTime;

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
public class Church extends BaseEntity {

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
    private Address address;

    private OffsetDateTime deletedAt;


    public void update(UpdateChurchRequest updateChurchRequest) {
        this.name = updateChurchRequest.name();
        this.pastor = Pastor.builder()
                .name(updateChurchRequest.pastorName())
                .phone(updateChurchRequest.pastorPhone())
                .build();
        this.address = Address.builder()
                .basic(updateChurchRequest.addressBasic())
                .detail(updateChurchRequest.addressDetail())
                .build();
        this.visitPurpose = updateChurchRequest.visitPurpose();
    }

}
