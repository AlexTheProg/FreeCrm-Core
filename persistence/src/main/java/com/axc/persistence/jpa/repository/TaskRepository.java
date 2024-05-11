package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.Task;
import com.axc.persistence.domain.charts.TaskLineChart;
import com.axc.persistence.domain.charts.TaskStatusBarChart;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends BaseJpaRepository<Task, Long> {

    @Query("select t.status as status, count(t) as count from Task t group by t.status")
    List<TaskStatusBarChart> countTaskByStatus();

//    @Query("""
//    SELECT CONCAT(wm.member.firstName, ' ', wm.member.lastName) AS name,
//           SUM(CASE WHEN t.status = 'DONE' THEN 1 ELSE 0 END) AS done,
//           SUM(CASE WHEN t.status = 'TODO' THEN 1 ELSE 0 END) AS todo,
//           SUM(CASE WHEN t.status = 'IN_PROGRESS' THEN 1 ELSE 0 END) AS in_progress,
//           SUM(CASE WHEN t.status = 'CLOSED' THEN 1 ELSE 0 END) AS closed
//    FROM Task t
//    JOIN WorkspaceMember wm ON t.workspaceMember = wm
//    GROUP BY CONCAT(wm.member.firstName, ' ', wm.member.lastName)
//""")
//    List<TaskLineChart> getTaskDistributionByUser();

    @Query("""
    SELECT t.status as status, count(t) as count, concat(wm.member.lastName, ' ', wm.member.firstName) as name from Task t 
    JOIN WorkspaceMember wm on t.workspaceMember = wm
    group by t.status, concat(wm.member.lastName, ' ', wm.member.firstName)
    """)
    List<TaskLineChart> getTaskDistributionByUser();
}
