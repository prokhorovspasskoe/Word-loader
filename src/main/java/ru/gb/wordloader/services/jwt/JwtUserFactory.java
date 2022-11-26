package ru.gb.wordloader.services.jwt;

import ru.gb.wordloader.entities.User;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getPassword(),
                user.getName()
        );
    }
}
