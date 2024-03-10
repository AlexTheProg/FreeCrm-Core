package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Lead extends AuditedEntity {

    @OneToMany
    private Set<Contact> contacts = new HashSet<>();

    @OneToOne
    private Company company;


}
