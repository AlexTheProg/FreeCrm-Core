package com.axc.web.presentation.controllers;

import com.axc.persistence.domain.metabase.DashboardType;
import com.axc.persistence.jpa.service.MetabaseService;
import com.axc.web.security.session.CurrentUserHolder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/metabase")
@ConditionalOnWebApplication
public class MetabaseController {
    private final MetabaseService metabaseService;
    private final CurrentUserHolder currentUserHolder;

    public MetabaseController(MetabaseService metabaseService, CurrentUserHolder currentUserHolder) {
        this.metabaseService = metabaseService;
        this.currentUserHolder = currentUserHolder;
    }

    @GetMapping("/home-dashboard")
    public String homeDashboard() {
        return metabaseService.getSignedMetabaseUrl(DashboardType.HOME_DASHBOARD);
    }
}
