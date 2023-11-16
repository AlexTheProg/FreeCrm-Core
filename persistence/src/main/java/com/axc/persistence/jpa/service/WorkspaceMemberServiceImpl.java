package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.WorkspaceMember;
import com.axc.persistence.jpa.repository.WorkspaceMemberRepository;
import org.springframework.stereotype.Service;

@Service
class WorkspaceMemberServiceImpl implements WorkspaceMemberService {

    private final WorkspaceMemberRepository workspaceMemberRepository;

    WorkspaceMemberServiceImpl(WorkspaceMemberRepository workspaceMemberRepository) {
        this.workspaceMemberRepository = workspaceMemberRepository;
    }

    @Override
    public WorkspaceMember saveOrUpdate(WorkspaceMember workspaceMember) {
        return workspaceMemberRepository.save(workspaceMember);
    }
}
