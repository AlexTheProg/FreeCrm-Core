package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "workspaces")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_workspace_id", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Workspace extends AuditedEntity {

    @Column(name = "industry", columnDefinition = "text")
    private String industry;

    @Column(name = "number_of_employees", columnDefinition = "bigint")
    private Long numberOfEmployees;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner_id")
    @MapsId
    private User owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workspace")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.PROTECTED)
    private Set<WorkspaceMember> members = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workspace")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.PROTECTED)
    private Set<Lead> leads = new HashSet<>();

    @Column(name = "rent", columnDefinition = "numeric")
    private BigDecimal rent;

    public void addMember(WorkspaceMember member) {
        members.add(member);
        member.setWorkspace(this);
    }

    public void removeMember(WorkspaceMember member) {
        member.setWorkspace(null);
        members.remove(member);
    }

    public void addAllMembers(Collection<WorkspaceMember> items) {
        items.forEach(this::addMember);
    }
}
