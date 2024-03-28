package com.samill.missionary_backend.bus.bus.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

@Entity
@Table
public class BusEntity {
    @Id
    private String id;

    @Column(name = "missionary_id")
    private String missionaryId;

    @Comment("버스 이름")
    private String name;

    @Comment("운행 타입")
    @Column(name = "driving_type")
    private BusDrivingTypeEntity drivingType;

    @Comment("버스 최대 수용 인원")
    @Column(name = "max_capacity")
    private int maxCapacity;

    @Comment("탑승 인원")
    @Column(name = "passenger_count")
    private int passengerCount;
}
