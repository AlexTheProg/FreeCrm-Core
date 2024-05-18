package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.metabase.DashboardType;
import com.axc.persistence.domain.metabase.MetabaseDashboardReference;
import com.axc.persistence.domain.metabase.MetabaseDashboardReferenceId;
import com.axc.persistence.jpa.BaseJpaRepository;
import com.axc.persistence.jpa.BaseJpaService;
import com.axc.persistence.jpa.repository.MetabaseDashboardReferenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class MetabaseDashboardReferenceService implements BaseJpaService<MetabaseDashboardReference, MetabaseDashboardReferenceId> {
    private final MetabaseDashboardReferenceRepository metabaseDashboardReferenceRepository;

    public MetabaseDashboardReferenceService(MetabaseDashboardReferenceRepository metabaseDashboardReferenceRepository) {
        this.metabaseDashboardReferenceRepository = metabaseDashboardReferenceRepository;
    }

    public MetabaseDashboardReference findByDashboardType(DashboardType dashboardType) {
        return metabaseDashboardReferenceRepository.findByDashboardType(dashboardType);
    }

    @Override
    public BaseJpaRepository<MetabaseDashboardReference, MetabaseDashboardReferenceId> repository() {
        return metabaseDashboardReferenceRepository;
    }
}
