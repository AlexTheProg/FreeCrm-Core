package com.axc.persistence.jpa.repository;

import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.domain.WorkspaceMember_;
import com.axc.persistence.domain.Workspace_;
import com.axc.persistence.jpa.BaseJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkspaceMemberRepository extends BaseJpaRepository<WorkspaceMember, Long> {

    @EntityGraph(attributePaths = {
            WorkspaceMember_.MEMBER,
            WorkspaceMember_.WORKSPACE + "." + Workspace_.MEMBERS
    })
    Optional<WorkspaceMember> findWorkspaceMemberByMemberId(Long id);
}
