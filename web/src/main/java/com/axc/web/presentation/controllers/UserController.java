package com.axc.web.presentation.controllers;

import com.axc.persistence.jpa.service.UserService;
import com.axc.persistence.jpa.service.WorkspaceMemberService;
import com.axc.persistence.jpa.service.WorkspaceService;
import com.axc.web.presentation.adapter.UserAdapter;
import com.axc.web.presentation.dto.UserDto;
import com.axc.web.presentation.facades.member.WorkspaceMemberManager;
import com.axc.web.security.session.CurrentUserHolder;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User entity endpoints")
@RestController
@RequestMapping(value = UserController.API_URI)
@Validated
@Slf4j
public class UserController {
    public static final String API_URI = "/api/users";
    private final UserService userService;
    private final UserAdapter userAdapter;
    private final WorkspaceMemberManager workspaceMemberManager;
    private final WorkspaceService workspaceService;
    private final WorkspaceMemberService workspaceMemberService;
    private final CurrentUserHolder currentUserHolder;

    public UserController(UserService userService,
                          UserAdapter userAdapter,
                          WorkspaceMemberManager workspaceMemberManager,
                          WorkspaceService workspaceService,
                          WorkspaceMemberService workspaceMemberService,
                          CurrentUserHolder currentUserHolder) {
        this.userService = userService;
        this.userAdapter = userAdapter;
        this.workspaceMemberManager = workspaceMemberManager;
        this.workspaceService = workspaceService;
        this.workspaceMemberService = workspaceMemberService;
        this.currentUserHolder = currentUserHolder;
    }

    @PostMapping("/{workspaceName}")
    public UserDto saveOrUpdate(@PathVariable String workspaceName, @RequestBody @Valid UserDto userDto) {
        var workspace = workspaceService.findByTenantId(workspaceName);
        var user = userAdapter.mapDtoToEntity(userDto);

        if (!user.isNew()) {
            var savedUser = userService.save(user);
            return userAdapter.mapEntityToDto(savedUser);
        }

        var savedWorkspaceMember = workspaceMemberManager.save(workspace, user);
        return userAdapter.mapEntityToDto(savedWorkspaceMember);
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll()
                .stream()
                .map(userAdapter::mapEntityToDto)
                .toList();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        if (!userService.existsById(userId)) {
            throw new RuntimeException("User not found");
        }

        var member = workspaceMemberService.findWithWorkspaceAndOwnerById(userId);
        if (member.getWorkspace().getOwner().getId().equals(userId)) {
            throw new RuntimeException("Cannot delete the owner of the workspace");
        }

        if (!currentUserHolder.getCurrentTenantId().equals(member.getTenantId())) {
            throw new RuntimeException("User is not part of your workspace");
        }

        workspaceMemberService.deleteById(userId);
    }

}
