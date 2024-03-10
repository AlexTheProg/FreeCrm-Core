package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_role_id", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Role extends AuditedEntity {
    @Column(name = "name", columnDefinition = "text", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<User> users = new HashSet<>();
}
