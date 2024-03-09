package com.axc.persistence.domain;

import com.axc.persistence.AuditedEntity;
import com.axc.persistence.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_task_id", allocationSize = 1)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Task extends AuditedEntity {

    @Column
    private String subject;

    @Column
    private String details;

    @Column
    private String identifier;

    @Column(columnDefinition = "date")
    private LocalDate startDate;

    @Column(columnDefinition = "date")
    private LocalDate endDate;

    @Column
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "workspace_member_id", nullable = false)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private WorkspaceMember workspaceMember;

}
