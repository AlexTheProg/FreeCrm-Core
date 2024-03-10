package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import com.axc.persistence.enums.LeadStatus;
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
@Table(name = "leads")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_lead_id", allocationSize = 1)
@Getter
@Setter
public class Lead extends AuditedEntity {

    @Column
    @Enumerated(value = EnumType.STRING)
    private LeadStatus status;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lead")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @Setter(AccessLevel.PRIVATE)
    private Set<Contact> contacts = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Company company;

    public void addContact(Contact contact) {
        contacts.add(contact);
        contact.setLead(this);
    }

    public void addAllContacts(Collection<Contact> items) {
        items.forEach(this::addContact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setLead(null);
    }

}
