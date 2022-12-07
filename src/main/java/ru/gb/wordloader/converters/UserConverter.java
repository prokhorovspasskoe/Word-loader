package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

public class UserConverter {
   public User convertFromDtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(user.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto convertFromEntityToDto(User user){
       UserDto userDto = new UserDto();
       userDto.setUsername(user.getName());
       userDto.setId(user.getId());
//       userDto.setPassword(user.getPassword());
       return userDto;
    }
}
