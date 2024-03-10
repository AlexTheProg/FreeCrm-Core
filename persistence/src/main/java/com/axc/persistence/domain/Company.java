package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "companies")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_company_id", allocationSize = 1)
@Getter
@Setter
public class Company extends AuditedEntity {

    @Column
    private String name;

    @Column
    private String details;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "company")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.PRIVATE)
    private Set<Branch> branches = new HashSet<>();

    public void addBranch(Branch branch) {
        branches.add(branch);
        branch.setCompany(this);
    }

    public void addAllBranches(Collection<Branch> items) {
        items.forEach(this::addBranch);
    }

    public void removeBranch(Branch branch) {
        branches.remove(branch);
    }
}
