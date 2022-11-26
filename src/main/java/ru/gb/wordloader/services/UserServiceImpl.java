package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {
        User registeredUser = new User();
        if(userDto.getPassword().equals(userDto.getMatchingPassword()))
        {
            if(userRepository.findFirstByName(userDto.getUsername()).getName() == null){
                registeredUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
                registeredUser.setName(userDto.getUsername());
                userRepository.save(registeredUser);
            }
        }
    }

    @Override
    public User findByName(String username) {
        return userRepository.findFirstByName(username);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
