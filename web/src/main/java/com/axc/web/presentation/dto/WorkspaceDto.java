package com.axc.web.presentation.dto;

import com.axc.persistence.domain.Workspace;

public class WorkspaceDto {

    public String industry;

    public Long numberOfEmployees;

    public UserDto owner;

    public WorkspaceDto(Workspace workspace) {
        industry = workspace.getIndustry();
        numberOfEmployees = workspace.getNumberOfEmployees();
        owner = new UserDto(workspace.getOwner());
    }
}
