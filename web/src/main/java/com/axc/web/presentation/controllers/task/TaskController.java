package com.axc.web.presentation.controllers.task;

import com.axc.persistence.jpa.service.TaskService;
import com.axc.web.presentation.dto.TaskDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User", description = "Task entity endpoints")
@RestController
@RequestMapping(value = TaskController.API_URI)
@Validated
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    public static final String API_URI = "/api/tasks";
    private final TaskService taskService;

    @GetMapping()
    public List<TaskDto> getTasks() {
        return List.of(new TaskDto(taskService.findAll().getFirst()));
    }
}
