package com.axc.web.presentation.adapter;

import com.axc.persistence.domain.Workspace;
import com.axc.web.presentation.dto.CreateWorkspaceDto;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceAdapter implements EntityDtoAdapter<Workspace, CreateWorkspaceDto> {

    private final UserAdapter userAdapter;

    public WorkspaceAdapter(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public CreateWorkspaceDto mapEntityToDto(Workspace workspace) {
        return new CreateWorkspaceDto(workspace);
    }

    @Override
    public Workspace mapDtoToEntity(CreateWorkspaceDto workspaceDto) {
        var workspace = new Workspace();

        workspace.setIndustry(workspaceDto.industry);
        workspace.setNumberOfEmployees(workspaceDto.numberOfEmployees);

        return workspace;
    }
}
