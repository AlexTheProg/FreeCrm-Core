package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.User;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseJpaRepository<User, Long> {
}
