package com.axc.web.presentation.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestOauth {

    @GetMapping("/test-oauth")
    public void test(){
        var securityContext = SecurityContextHolder.getContext();
    }
}
