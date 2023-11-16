package com.axc.web.presentation.facades.workspace;

import com.axc.persistence.domain.User;
import com.axc.persistence.domain.Workspace;
import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.jpa.service.UserService;
import com.axc.persistence.jpa.service.WorkspaceMemberService;
import com.axc.persistence.jpa.service.WorkspaceService;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceManager {
    private final WorkspaceService workspaceService;
    private final WorkspaceMemberService workspaceMemberService;
    private final UserService userService;

    public WorkspaceManager(WorkspaceService workspaceService,
                            WorkspaceMemberService workspaceMemberService,
                            UserService userService) {
        this.workspaceService = workspaceService;
        this.workspaceMemberService = workspaceMemberService;
        this.userService = userService;
    }

    public Workspace onboardOwnerInWorkspace(Workspace workspace, User owner) {
        var savedWorkspace = workspaceService.saveOrUpdate(workspace);
        var savedOwner = userService.saveOrUpdate(owner);

        var workspaceMember = new WorkspaceMember();
        workspaceMember.setWorkspace(savedWorkspace);
        workspaceMember.setMember(savedOwner);
        workspaceMemberService.saveOrUpdate(workspaceMember);

        return savedWorkspace;
    }
}
