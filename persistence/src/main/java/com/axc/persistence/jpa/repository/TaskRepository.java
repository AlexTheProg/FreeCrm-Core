package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.TaskStatusBarChart;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends BaseJpaRepository<Task, Long> {

    @Query("select t.status as status, count(t) as count from Task t group by t.status")
    List<TaskStatusBarChart> countTaskByStatus();
}
