package com.axc.web.presentation.facades.member;

import com.axc.persistence.domain.User;
import com.axc.persistence.domain.Workspace;
import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.jpa.service.UserService;
import com.axc.persistence.jpa.service.WorkspaceMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Create and/or update {@link com.axc.persistence.domain.WorkspaceMember}
 * in a {@link com.axc.persistence.domain.Workspace}
 */
@Service
@Slf4j
public class WorkspaceMemberManager {
    private final UserService userService;
    private final WorkspaceMemberService workspaceMemberService;

    public WorkspaceMemberManager(UserService userService, WorkspaceMemberService workspaceMemberService) {
        this.userService = userService;
        this.workspaceMemberService = workspaceMemberService;
    }

    @Transactional
    public User save(Workspace workspace, User user) {
        var workspaceMember = new WorkspaceMember();
        workspaceMember.setMember(user);
        workspace.addMember(workspaceMember);

        var savedWorkspaceMember = workspaceMemberService.save(workspaceMember);

        return savedWorkspaceMember.getMember();
    }
}
