package com.axc.web.presentation.adapter;

import com.axc.persistence.domain.User;
import com.axc.web.presentation.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements EntityDtoAdapter<User, UserDto> {
    @Override
    public UserDto mapEntityToDto(User user) {
        return new UserDto(user);
    }

    @Override
    public User mapDtoToEntity(UserDto userDto) {
        var user = new User();
        user.setEmail(userDto.email);
        user.setFirstName(userDto.firstName);
        user.setLastName(userDto.lastName);

        return user;
    }
}
