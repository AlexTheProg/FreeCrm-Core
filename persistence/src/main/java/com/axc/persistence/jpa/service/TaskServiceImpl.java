package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.TaskStatusBarChart;
import com.axc.persistence.jpa.BaseJpaRepository;
import com.axc.persistence.jpa.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public BaseJpaRepository<Task, Long> repository() {
        return taskRepository;
    }

    @Override
    public List<TaskStatusBarChart> findTaskStatusBarChart() {
        return taskRepository.countTaskByStatus();
    }
}
