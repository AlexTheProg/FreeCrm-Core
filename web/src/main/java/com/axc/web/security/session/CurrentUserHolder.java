package com.axc.web.security.session;

import com.axc.persistence.domain.User;
import com.axc.persistence.jpa.service.UserService;
import com.axc.web.security.SecurityUtils;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

@Service
public class CurrentUserHolder {
    private final UserService userService;

    public CurrentUserHolder(UserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        verifyAuthentication();

        return SecurityUtils.getCurrentUserIdentifier().orElseThrow();
    }

    public Long getCurrentTenantId() {
        verifyAuthentication();

        return SecurityUtils.getCurrentTenant().orElseThrow();
    }

    public User get() {
        final String email = getEmail();

        var user = userService.findByEmail(email);
        if (user == null) {
            throw new NotFoundException(email + " not found");
        }

        return user;
    }

    private void verifyAuthentication() {
        if (!SecurityUtils.isAuthenticated()) {
            throw new AccessDeniedException("Access is denied");
        }
    }
}
