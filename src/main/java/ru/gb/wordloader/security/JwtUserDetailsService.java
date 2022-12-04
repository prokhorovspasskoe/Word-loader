package ru.gb.wordloader.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.security.jwt.JwtUser;
import ru.gb.wordloader.security.jwt.JwtUserFactory;
import ru.gb.wordloader.services.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username);
        if(user == null)
        {
            throw new UsernameNotFoundException("User with username - " + username + " not found.");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}
