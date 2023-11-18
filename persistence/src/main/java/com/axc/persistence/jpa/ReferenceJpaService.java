package com.axc.persistence.jpa;

import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public interface ReferenceJpaService<T extends Persistable<ID>, ID extends Serializable>
    extends BaseJpaService<T, ID> {

    /**
     * If the id is not null, get an instance with the specified id from the repository.
     * If the id is null, create a new instance.
     */
    T createReferenceForId(@Nullable ID id);
}
