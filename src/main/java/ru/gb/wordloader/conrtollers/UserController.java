package ru.gb.wordloader.conrtollers;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@Schema(description = "Информация о пользователе и его прогрессе")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById/{user_id}")
    @Schema(description = "Получение информации о пользователе по id.")
    public UserDto getUserAllInfo(@PathVariable  long user_id){
        return userService.getUserAllInfo(user_id);
    }

    @GetMapping("/getByName/{user_name}")
    @Schema(description = "Получение информации о пользователе по userName.")
    public UserDto getUserAllInfo(@PathVariable  String user_name){

        return userService.getUserAllInfo(user_name);
    }

    // TODO
    //   По информации Александра метод не работает, проверить, при необходимости исправить
    @GetMapping("/get/current-user")
    @Schema(description = "Получение информации о пользователе по username в http-заголовке")
    public UserDto getUserAllInfo(HttpServletRequest request){
        String username = request.getRemoteUser();
        return userService.getUserAllInfo(username);
    }
}
