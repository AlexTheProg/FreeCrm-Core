package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "branches")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_branch_id", allocationSize = 1)
@Getter
@Setter
public class Branch extends AuditedEntity {

    @Column
    private String name;

    @Column
    private String details;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Company company;
}
