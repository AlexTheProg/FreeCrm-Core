package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Collection;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "workspace_member_files", joinColumns = @JoinColumn(name = "workspace_member_id"),
        inverseJoinColumns = @JoinColumn(name = "document_id"))
    private Set<Document> documents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "workspace_member_deals", joinColumns = @JoinColumn(name = "workspace_member_id"),
            inverseJoinColumns = @JoinColumn(name = "deal_id"))
    private Set<Deal> deals = new HashSet<>();

    public void addTask(Task task) {
        tasks.add(task);
        task.setWorkspaceMember(this);
    }

    public void addAllTasks(Collection<Task> items) {
        items.forEach(this::addTask);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setWorkspaceMember(null);
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }

    public void addDeal(Deal deal) {
        deals.add(deal);
        deal.setWorkspaceMember(this);
    }

    public void removeDeal(Deal deal) {
        deals.remove(deal);
        deal.setWorkspaceMember(null);
    }

    public void addAllDeals(Collection<Deal> items) {
        items.forEach(this::addDeal);
    }
}
