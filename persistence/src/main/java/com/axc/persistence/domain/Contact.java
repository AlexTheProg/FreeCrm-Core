package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "contacts")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_contact_id", allocationSize = 1)
@Getter
@Setter
public class Contact extends AuditedEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "lead_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Lead lead;
}
