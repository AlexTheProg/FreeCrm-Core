package com.axc.web.presentation.controllers;

import com.axc.web.presentation.adapter.UserAdapter;
import com.axc.web.presentation.adapter.WorkspaceAdapter;
import com.axc.web.presentation.dto.CreateWorkspaceDto;
import com.axc.web.presentation.facades.workspace.WorkspaceManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Onboarding", description = "Onboarding flow endpoints")
@RestController
@RequestMapping(value = OnboardController.API_URI)
@Validated
@Slf4j
public class OnboardController {
    public static final String API_URI = "/api/onboard";

    private final WorkspaceAdapter workspaceAdapter;
    private final WorkspaceManager workspaceManager;
    private final UserAdapter userAdapter;

    public OnboardController(WorkspaceAdapter workspaceAdapter,
                             WorkspaceManager workspaceManager,
                             UserAdapter userAdapter) {
        this.workspaceAdapter = workspaceAdapter;
        this.workspaceManager = workspaceManager;
        this.userAdapter = userAdapter;
    }

    @PostMapping("/create")
    public CreateWorkspaceDto onboardOwnerInWorkspace(@RequestBody @Valid CreateWorkspaceDto createWorkspaceDto) {
        var workspace = workspaceAdapter.mapDtoToEntity(createWorkspaceDto);
        var owner = userAdapter.mapDtoToEntity(createWorkspaceDto.owner);

        return workspaceAdapter.mapEntityToDto(workspaceManager.onboardOwnerInWorkspace(workspace, owner));
    }
}
