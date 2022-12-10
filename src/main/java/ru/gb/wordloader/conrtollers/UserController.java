package ru.gb.wordloader.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{user_id}")
    public UserDto getUserAllInfo(@PathVariable  long user_id){
        return userService.getUserAllInfo(user_id);
    }

    @GetMapping("/get/current-user")
    public UserDto getUserAllInfo(HttpServletRequest request){
        String username = request.getRemoteUser();
        return userService.getUserAllInfo(username);
    }
}
