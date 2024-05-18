package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.metabase.DashboardType;
import com.axc.persistence.domain.metabase.MetabaseDashboardReference;
import com.axc.persistence.domain.metabase.MetabaseDashboardReferenceId;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MetabaseDashboardReferenceRepository extends BaseJpaRepository<MetabaseDashboardReference, MetabaseDashboardReferenceId> {
    @Query("select r from MetabaseDashboardReference r where r.id.dashboardType = :dashboardType")
    MetabaseDashboardReference findByDashboardType(DashboardType dashboardType);
}
