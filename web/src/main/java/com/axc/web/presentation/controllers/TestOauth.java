package com.axc.web.presentation.controllers;

import com.axc.web.security.session.CurrentUserHolder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestOauth {

    private final CurrentUserHolder currentUserHolder;

    @PostMapping("/api/test-oauth")
    public void test() {
        var test = currentUserHolder.get();
    }
}
