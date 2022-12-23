package ru.gb.wordloader.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.wordloader.dto.RegistrationUserDto;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.services.UserService;

@RestController
@RequestMapping(value = "/api/v1/registration")
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationUserDto registerDto) {
        String username = registerDto.getUsername();
        String password  = registerDto.getPassword();
        if(username != null && password != null){
            User user = userService.findByName(username);
            if (user != null) {
                return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
            }
            if ( userService.register(registerDto) ) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Password and it's confirmation mismatch", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("Empty username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
