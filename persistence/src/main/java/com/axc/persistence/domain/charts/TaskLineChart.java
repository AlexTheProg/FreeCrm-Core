package com.axc.persistence.domain.charts;

import com.axc.persistence.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Value;

public interface TaskLineChart {
    String getName();

    Long getDone();

    Long getTodo();

    @Value("#{target.in_progress}")
    Long getInProgress();

    Long getClosed();
}
