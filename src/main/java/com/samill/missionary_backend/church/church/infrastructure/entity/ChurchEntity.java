package com.samill.missionary_backend.church.church.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;

@Entity
@Table
public class ChurchEntity {
    @Id
    private String id;

    @Column
    private String name;

    @Comment("담임 목사 이름")
    @Column
    private String seniorPastorName;


    @Comment("주소")
    @Column
    private String address;


}
