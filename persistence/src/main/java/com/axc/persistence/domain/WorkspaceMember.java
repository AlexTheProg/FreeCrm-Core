package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workspace_members")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_workspace_member_id", allocationSize = 1)
@Getter
@Setter
public class WorkspaceMember extends AuditedEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId
    private User member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workspace_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Workspace workspace;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workspaceMember")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.PROTECTED)
    private Set<Task> tasks = new HashSet<>();
}
