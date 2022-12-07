package ru.gb.wordloader.dto;

import lombok.Data;

/**
 * DTO будем использовать для авторизации. В ней приходят логин и пароль с фронта.
 *
 *
 * */

@Data
public class AuthenticationUserDto {
    private String username;
    private String password;
}
