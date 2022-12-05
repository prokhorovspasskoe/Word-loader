package ru.gb.wordloader.conrtollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.services.PersonalAccountService;
import ru.gb.wordloader.services.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/word")
@CrossOrigin
public class WordController {
    private final PersonalAccountService personalAccountService;
    @Autowired
    public WordController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WordDto> getWord(@PathVariable("id")Long id){
        WordDto wordDto = personalAccountService.findWordById(id);
        return new ResponseEntity<>(wordDto, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
