package com.axc.web.presentation.controllers.task;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.tasks.TaskLineChart;
import com.axc.persistence.domain.charts.tasks.TaskStatusBarChart;
import com.axc.persistence.jpa.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Tasks", description = "Task entity endpoints")
@RestController
@RequestMapping(value = TaskController.API_URI)
@Validated
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    public static final String API_URI = "/api/tasks";
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskStatusBarChart>> getTaskStatusBarChart() {
        System.out.println(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        return ResponseEntity.ok(taskService.findTaskStatusBarChart());
    }

    @GetMapping("/task-distribution-by-user")
    public ResponseEntity<List<TaskLineChart>> getTaskLineChart() {
        return ResponseEntity.ok(taskService.findTaskLineChart());
    }

    @GetMapping("/all")
    public List<Task> getAll() {
        return taskService.findAll();
    }
}
