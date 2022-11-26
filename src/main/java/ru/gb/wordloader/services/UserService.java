package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

import java.util.Optional;

public interface UserService {
    void register(UserDto userDto);

    User findByName(String Username);

    Optional<User> findById(Long id);

    void deleteUser(long id);
}
