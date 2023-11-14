package com.axc.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Getter
@Setter
public class AuditedEntity extends BaseEntity {

    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Instant createdDate;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "text")
    protected String createdBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    protected Instant updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by", columnDefinition = "text")
    protected String updatedBy;
}
