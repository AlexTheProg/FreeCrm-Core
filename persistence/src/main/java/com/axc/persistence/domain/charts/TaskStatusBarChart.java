package com.axc.persistence.domain.charts;


import com.axc.persistence.enums.TaskStatus;

public interface TaskStatusBarChart {
   Long getCount();
   TaskStatus getStatus();
}
