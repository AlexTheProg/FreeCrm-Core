package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.TaskLineChart;
import com.axc.persistence.domain.charts.TaskStatusBarChart;
import com.axc.persistence.domain.charts.UserTaskDTO;
import com.axc.persistence.enums.TaskStatus;
import com.axc.persistence.jpa.BaseJpaRepository;
import com.axc.persistence.jpa.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EntityManager em;

    @Override
    public BaseJpaRepository<Task, Long> repository() {
        return taskRepository;
    }

    @Override
    public List<TaskStatusBarChart> findTaskStatusBarChart() {
        return taskRepository.countTaskByStatus();
    }

    @Override
    public List<TaskLineChart> findTaskLineChart() {
        String jpql = "SELECT t.status, CONCAT(wm.member.firstName, ' ', wm.member.lastName), COUNT(t.id) " +
                "FROM Task t " +
                "JOIN t.workspaceMember wm " +
                "GROUP BY t.status, CONCAT(wm.member.firstName, ' ', wm.member.lastName)";

        List<Object[]> resultList = em.createQuery(jpql, Object[].class)
                .getResultList();

        return resultList.stream()
                .collect(Collectors.groupingBy(row -> (TaskStatus) row[0]))
                .entrySet().stream()
                .map(entry -> new TaskLineChart(
                        entry.getKey(),
                        entry.getValue().stream()
                                .map(row -> new UserTaskDTO((String) row[1], (Long) row[2]))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}

