package com.axc.web.presentation.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User entity endpoints")
@RestController
@RequestMapping(value = UserController.API_URI)
@Validated
@Slf4j
public class UserController {
    public static final String API_URI = "/api/users";

}
