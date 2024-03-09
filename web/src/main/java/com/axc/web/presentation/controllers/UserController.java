package com.axc.web.presentation.controllers;

import com.axc.persistence.domain.User;
import com.axc.persistence.domain.User_;
import com.axc.persistence.jpa.service.UserService;
import com.axc.persistence.jpa.service.WorkspaceService;
import com.axc.web.presentation.adapter.UserAdapter;
import com.axc.web.presentation.dto.UserDto;
import com.axc.web.presentation.facades.member.WorkspaceMemberManager;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public UserController(UserService userService,
                          UserAdapter userAdapter,
                          WorkspaceMemberManager workspaceMemberManager,
                          WorkspaceService workspaceService) {
        this.userService = userService;
        this.userAdapter = userAdapter;
        this.workspaceMemberManager = workspaceMemberManager;
        this.workspaceService = workspaceService;
    }

    @PostMapping("/{workspaceId}")
    public UserDto saveOrUpdate(@PathVariable Long workspaceId, @RequestBody @Valid UserDto userDto) {
        var workspace = workspaceService.findById(workspaceId);
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

}
