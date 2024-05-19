package com.axc.web.config;

import com.axc.web.security.session.CurrentUserHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class TenantIdFilter extends OncePerRequestFilter {
    private final CurrentUserHolder currentUserHolder;
    private final TenantIdentifierResolver tenantIdentifierResolver;

    public TenantIdFilter(CurrentUserHolder currentUserHolder, TenantIdentifierResolver tenantIdentifierResolver) {
        this.currentUserHolder = currentUserHolder;
        this.tenantIdentifierResolver = tenantIdentifierResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().contains("/ws")
        || request.getRequestURI().contains("/app")) {
            //pass on to other possible filters and do nothing
            filterChain.doFilter(request, response);
            return;
        }

        tenantIdentifierResolver.setTenantId(currentUserHolder.getCurrentTenantId());

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
    }
}
