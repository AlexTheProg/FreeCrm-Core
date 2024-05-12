package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.tasks.TaskLineChart;
import com.axc.persistence.domain.charts.tasks.TaskStatusBarChart;
import com.axc.persistence.jpa.BaseJpaService;

import java.util.List;

public interface TaskService extends BaseJpaService<Task, Long> {
    List<TaskStatusBarChart> findTaskStatusBarChart();
    List<TaskLineChart> findTaskLineChart();
}
