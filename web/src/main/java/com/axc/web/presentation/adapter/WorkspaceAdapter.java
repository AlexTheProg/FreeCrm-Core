package com.axc.web.presentation.adapter;

import com.axc.persistence.domain.Workspace;
import com.axc.web.presentation.dto.WorkspaceDto;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceAdapter implements EntityDtoAdapter<Workspace, WorkspaceDto> {

    private final UserAdapter userAdapter;

    public WorkspaceAdapter(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public WorkspaceDto mapEntityToDto(Workspace workspace) {
        return new WorkspaceDto(workspace);
    }

    @Override
    public Workspace mapDtoToEntity(WorkspaceDto workspaceDto) {
        var workspace = new Workspace();

        workspace.setIndustry(workspaceDto.industry);
        workspace.setNumberOfEmployees(workspaceDto.numberOfEmployees);
        workspace.setOwner(userAdapter.mapDtoToEntity(workspaceDto.owner));

        return workspace;
    }
}
