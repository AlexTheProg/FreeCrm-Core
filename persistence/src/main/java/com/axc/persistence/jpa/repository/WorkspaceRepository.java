package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
}
