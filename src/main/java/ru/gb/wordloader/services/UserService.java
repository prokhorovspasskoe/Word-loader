package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.RegistrationUserDto;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

import java.util.Optional;

public interface UserService {
    boolean register(RegistrationUserDto registrationUserDto);

    User findByName(String Username);

    Optional<User> findById(Long id);

    void deleteUser(long id);

    UserDto getUserAllInfo(long user_id);

    UserDto getUserAllInfo(String username);
}
