package com.axc.persistence.domain.charts;

import lombok.Getter;

@Getter
public class UserTaskDTO {

    private String name;
    private Long count;

    public UserTaskDTO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
