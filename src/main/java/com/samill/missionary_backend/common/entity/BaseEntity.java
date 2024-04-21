package com.samill.missionary_backend.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Getter
@Accessors(chain = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    protected OffsetDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    protected String createdBy;

    @LastModifiedDate
    protected OffsetDateTime updatedAt;

    @LastModifiedBy
    protected String updatedBy;

    @Version
    protected Integer version = 0;


}
