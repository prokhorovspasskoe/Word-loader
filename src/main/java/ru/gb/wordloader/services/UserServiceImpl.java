package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.dto.RegistrationUserDto;
import ru.gb.wordloader.entities.Role;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.repositories.RoleRepository;
import ru.gb.wordloader.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(RegistrationUserDto registrationUserDto) {

        if(registrationUserDto.getPassword().equals(registrationUserDto.getMatchingPassword()))
        {
            // Мы в контроллере уже проверили, что такого логина нет, зачем ещё раз?

            // И потом, если USER по username не будет найден, то вызов getName() вызовет
            // Null Pointer Exception.
            //
            // Аннотацию @Builder я для чего ставлю? Сергей, почитай про шаблон проектирования Builder.
            // У всех же был курс "Архитектуры и шаблоны проектирования на Java"?

            /*
            if(userRepository.findFirstByName(registrationUserDto.getUsername()).getName() == null){
                Role roleUser = roleRepository.findByName("ROLE_USER");
                List<Role> userRoles = new ArrayList<>();
                userRoles.add(roleUser);
                User user = new User();
                user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
                user.setRoles(userRoles);
                user.setName(registrationUserDto.getUsername());
                userRepository.save(user);
            }*/

            List<Role> userRoles = new ArrayList<>();
            Role userRole = roleRepository.findByName("ROLE_USER");
            if (userRole != null) {
                userRoles.add(userRole);
            }

            User user = User.builder()
                    .name(registrationUserDto.getUsername())
                    .password(passwordEncoder.encode(registrationUserDto.getPassword()))
                    .roles(userRoles)
                    .build();
            userRepository.save(user);
            return true;
        } else {
            return false; //Мы должны пользователю сообщить, что пароль и подтверждение не совпадают
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
