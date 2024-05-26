package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.domain.WorkspaceMember_;
import com.axc.persistence.domain.Workspace_;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;

public interface WorkspaceMemberRepository extends BaseJpaRepository<WorkspaceMember, Long> {

    @EntityGraph(attributePaths = {
            WorkspaceMember_.MEMBER
    })
    Optional<WorkspaceMember> findWithMemberByMemberId(Long id);

    @EntityGraph(attributePaths = {
            WorkspaceMember_.WORKSPACE + "." + Workspace_.OWNER
    })
    WorkspaceMember findWithWorkspaceAndOwnerById(Long id);
}
