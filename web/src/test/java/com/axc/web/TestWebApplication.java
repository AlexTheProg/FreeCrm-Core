package com.axc.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestWebApplication {

    public static void main(String[] args) {
        SpringApplication.from(WebApplication::main).with(TestWebApplication.class).run(args);
    }

}
