package com.axc.web.presentation.dto;

import com.axc.persistence.domain.Workspace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateWorkspaceDto {
    public Long id;

    public String industry;

    public Long numberOfEmployees;

    public UserDto owner;

    public CreateWorkspaceDto(Workspace workspace) {
        id = workspace.getId();
        industry = workspace.getIndustry();
        numberOfEmployees = workspace.getNumberOfEmployees();
        owner = new UserDto(workspace.getOwner());
    }
}
