package com.axc.persistence.domain.metabase;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MetabaseDashboardReferenceId implements Serializable {
    @Column(name = "metabase_dashboard_id", nullable = false, unique = true)
    private Long metabaseDashboardId;

    @Column(name = "dashboard_type", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private DashboardType dashboardType;
}
