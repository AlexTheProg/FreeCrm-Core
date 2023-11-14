package com.axc.web.presentation.dto;

import com.axc.persistence.domain.User;

public class UserDto {

    public String firstName;

    public String lastName;

    public String email;

    public UserDto(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
    }
}
