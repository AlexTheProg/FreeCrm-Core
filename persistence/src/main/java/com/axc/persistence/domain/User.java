package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_user_id", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class User extends AuditedEntity {

    @Column(name = "first_name", columnDefinition = "text")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "text")
    private String lastName;

    @Column(name = "email", columnDefinition = "text")
    private String email;

    @Column(name = "username", columnDefinition = "text")
    private String username;

    @Column(name = "password", columnDefinition = "text")
    private String password;
}
