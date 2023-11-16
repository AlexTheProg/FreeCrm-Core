package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
