package com.axc.persistence.domain.charts.tasks;


import com.axc.persistence.enums.TaskStatus;

public interface TaskStatusBarChart {
   Long getCount();
   TaskStatus getStatus();
}
