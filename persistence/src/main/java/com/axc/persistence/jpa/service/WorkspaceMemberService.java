package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.jpa.ReferenceJpaService;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;

public interface WorkspaceMemberService extends ReferenceJpaService<WorkspaceMember, Long> {
    @Override
    @Transactional(readOnly = true)
    default WorkspaceMember createReferenceForId(@Nullable Long id) {
        return ObjectUtils.isNotEmpty(id) ? getReferenceById(id) : new WorkspaceMember();
    }
}
