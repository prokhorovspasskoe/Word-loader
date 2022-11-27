package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
<<<<<<< HEAD
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

=======
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

>>>>>>> origin/dev_services
    @Override
    public void register(UserDto userDto) {

        if(userDto.getPassword().equals(userDto.getMatchingPassword()))
        {
            if(userRepository.findFirstByName(userDto.getUsername()).getName() == null){
<<<<<<< HEAD
                User registeredUser = new User();
                Role roleUser = roleRepository.findByName("ROLE_USER");
                List<Role> userRoles = new ArrayList<>();
                userRoles.add(roleUser);
                registeredUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
                registeredUser.setRoles(userRoles);
                registeredUser.setName(userDto.getUsername());
                userRepository.save(registeredUser);
=======
                Role roleUser = roleRepository.findByName("ROLE_USER");
                List<Role> userRoles = new ArrayList<>();
                userRoles.add(roleUser);
                User user = new User();
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
                user.setRoles(userRoles);
                user.setName(userDto.getUsername());
                userRepository.save(user);
>>>>>>> origin/dev_services
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
