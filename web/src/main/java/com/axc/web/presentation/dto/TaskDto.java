package com.axc.web.presentation.dto;

import com.axc.persistence.domain.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskDto {
    public Long id;

    public TaskDto(Task task) {
        id = task.getId();
    }
}
