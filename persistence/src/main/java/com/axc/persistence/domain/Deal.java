package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import com.axc.persistence.enums.DealStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deals")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_deal_id", allocationSize = 1)
@Getter
@Setter
public class Deal extends AuditedEntity {
    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "details", columnDefinition = "text")
    private String details;

    @Column(name = "status", columnDefinition = "text")
    @Enumerated(EnumType.STRING)
    private DealStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "workspace_member_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private WorkspaceMember workspaceMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "company_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Company company;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "deal_files", joinColumns = @JoinColumn(name = "deal_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private Set<Document> documents = new HashSet<>();

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }
}
