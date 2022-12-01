package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

public class UserConverter {
    User convert(UserDto userDto){
        User user = new User();
        user.setName(user.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
