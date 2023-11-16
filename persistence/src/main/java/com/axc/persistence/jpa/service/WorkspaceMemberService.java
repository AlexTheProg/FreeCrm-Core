package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.WorkspaceMember;

public interface WorkspaceMemberService {
    WorkspaceMember saveOrUpdate(WorkspaceMember workspaceMember);
}
