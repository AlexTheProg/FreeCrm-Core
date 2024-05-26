package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.Workspace;
import com.axc.persistence.domain.Workspace_;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface WorkspaceRepository extends BaseJpaRepository<Workspace, Long> {
    @EntityGraph(attributePaths = {
            Workspace_.MEMBERS
    })
    Workspace findByTenantId(String tenantId);
}
