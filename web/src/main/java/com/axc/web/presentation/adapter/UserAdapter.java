package com.axc.web.presentation.adapter;

import com.axc.persistence.domain.User;
import com.axc.persistence.jpa.service.UserService;
import com.axc.web.presentation.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements EntityDtoAdapter<User, UserDto> {

    private final UserService userService;

    public UserAdapter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto mapEntityToDto(User user) {
        return new UserDto(user);
    }

    @Override
    public User mapDtoToEntity(UserDto userDto) {
        final var user = userService.createReferenceForId(userDto.id);

        user.setEmail(userDto.email);
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);

        return user;
    }
}
