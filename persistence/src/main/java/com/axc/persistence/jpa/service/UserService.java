package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.User;

public interface UserService {
    User saveOrUpdate(User user);
}
