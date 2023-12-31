package com.axc.web.presentation.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Workspace", description = "Workspace entity endpoints")
@RestController
@RequestMapping(value = WorkspaceController.API_URI)
@Validated
@Slf4j
public class WorkspaceController {
    public static final String API_URI = "/api/workspaces";


}
