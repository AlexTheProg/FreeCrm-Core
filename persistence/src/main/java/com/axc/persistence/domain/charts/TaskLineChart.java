package com.axc.persistence.domain.charts;

import com.axc.persistence.enums.TaskStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class TaskLineChart {
    private TaskStatus status;
    private List<UserTaskDTO> users;

    public TaskLineChart(TaskStatus status, List<UserTaskDTO> users) {
        this.status = status;
        this.users = users;
    }
}

