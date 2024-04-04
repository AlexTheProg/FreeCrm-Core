package com.axc.web.security.session;

import com.axc.web.security.SecurityUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        return SecurityUtils.getCurrentUserIdentifier();
    }
}
