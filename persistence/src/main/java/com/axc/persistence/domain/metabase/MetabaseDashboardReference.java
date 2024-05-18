package com.axc.persistence.domain.metabase;

import com.axc.persistence.BaseEntityEmbedded;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "metabase_dashboard_references")
@SequenceGenerator(name = "default_id_seq_gen", sequenceName = "seq_metabase_dashboard_reference_id", allocationSize = 10)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
public class MetabaseDashboardReference extends BaseEntityEmbedded<MetabaseDashboardReferenceId> {
    public MetabaseDashboardReference(Long metabaseDashboardId, DashboardType type) {
        setId(new MetabaseDashboardReferenceId(metabaseDashboardId, type));
    }
}
