package com.axc.persistence.jpa.service;

import com.axc.persistence.domain.User;
import com.axc.persistence.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }
}
