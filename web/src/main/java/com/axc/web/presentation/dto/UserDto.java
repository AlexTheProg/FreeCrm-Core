package com.axc.web.presentation.dto;

import com.axc.persistence.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {

    public Long id;

    public String firstName;

    public String lastName;

    public String email;

    public UserDto(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
    }
}
