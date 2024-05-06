package com.axc.persistence.jpa;

import jakarta.ws.rs.NotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.Serializable;
import java.util.List;

public interface BaseJpaService<T extends Persistable<ID>, ID extends Serializable> {

    @Transactional(readOnly = true)
    default T findOne(final Specification<T> spec) {
        var found = repository().findAll(spec);

        if (found.isEmpty()) {
            throw new NotFoundException();
        }

        if (found.size() > 1) {
            throw new IllegalStateException("More than one entity with the same id!");
        }

        return found.get(0);
    }

    @Transactional(readOnly = true)
    default T getReferenceById(@NotNull ID id) {
        return repository().getReferenceById(id);
    }

    @Transactional(readOnly = true)
    default List<T> findAll(Sort sort) {
        return repository().findAll(sort);
    }

    @Transactional(readOnly = true)
    default List<T> findAll(final Specification<T> spec) {
        return repository().findAll(spec);
    }

    @Transactional(readOnly = true)
    default Page<T> findAll(final Pageable pageable) {
        return repository().findAll(pageable);
    }

    @Transactional(readOnly = true)
    default List<T> findAll() {
        return repository().findAll();
    }

    @Transactional(readOnly = true)
    default T findById(final ID id) {
        return repository().findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional(readOnly = true)
    default List<T> findByIdIn(final List<ID> ids) {
        return repository().findAllById(ids);
    }

    @Transactional(readOnly = true)
    default boolean existsById(final ID id) {
        return repository().existsById(id);
    }

    @Transactional(readOnly = false)
    default <S extends T> S save(final S entity) {
        return repository().save(entity);
    }

    @Transactional(readOnly = false)
    default <S extends T> List<S> saveAll(final Iterable<S> entities) {
        return repository().saveAll(entities);
    }

    @Transactional(readOnly = false)
    default void delete(T entity) {
        repository().delete(entity);
    }

    @Transactional(readOnly = false)
    default void deleteById(final ID id) {
        repository().deleteById(id);
    }

    BaseJpaRepository<T, ID> repository();
}
