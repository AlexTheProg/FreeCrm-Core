package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.User;
import com.axc.persistence.jpa.ReferenceJpaService;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends ReferenceJpaService<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

    @Override
    @Transactional(readOnly = true)
    default User createReferenceForId(@Nullable Long id) {
        return ObjectUtils.isNotEmpty(id) ? getReferenceById(id) : new User();
    }
}
