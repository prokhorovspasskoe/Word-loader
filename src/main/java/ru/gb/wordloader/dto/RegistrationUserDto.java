package ru.gb.wordloader.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String matchingPassword;
}
