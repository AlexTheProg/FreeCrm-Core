package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Workspace;

public interface WorkspaceService {
    Workspace saveOrUpdate(Workspace workspace);
}
