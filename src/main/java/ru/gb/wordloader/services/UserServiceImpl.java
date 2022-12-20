package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.UserConverter;
import ru.gb.wordloader.dto.RegistrationUserDto;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.Role;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.repositories.RoleRepository;
import ru.gb.wordloader.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(RegistrationUserDto registrationUserDto) {

        if(registrationUserDto.getPassword().equals(registrationUserDto.getMatchingPassword()))
        {
            List<Role> userRoles = new ArrayList<>();
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole != null) {
                userRoles.add(userRole);
            }else{
                return false;
            }

            User user = User.builder()
                    .name(registrationUserDto.getUsername())
                    .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                    .roles(userRoles)
                    .build();
            userRepository.save(user);
            return true;
        } else {
            return false;
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

    @Override
    public UserDto getUserAllInfo(long user_id) {
        User user = userRepository.getReferenceById(user_id);
        return UserConverter.convertToDto(user);
    }

    @Override
    public UserDto getUserAllInfo(String username) {
        User user = userRepository.findFirstByName(username);
        return UserConverter.convertToDto(user);
    }

    //Получаем пользователя, под которым авторизовались
    @Override
    public User getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByName(auth.getName());
    }

}
