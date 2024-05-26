package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Workspace;
import com.axc.persistence.jpa.BaseJpaRepository;
import com.axc.persistence.jpa.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    WorkspaceServiceImpl(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }


    @Override
    public BaseJpaRepository<Workspace, Long> repository() {
        return workspaceRepository;
    }

    @Override
    public Workspace findByTenantId(String tenantId) {
        return workspaceRepository.findByTenantId(tenantId);
    }
}
