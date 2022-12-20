package ru.gb.wordloader.conrtollers;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.services.PersonalAccountService;
import ru.gb.wordloader.services.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/word")
@CrossOrigin
@Schema(description = "Методы работы со словами.")
public class WordController {
    private final PersonalAccountService personalAccountService;
    @Autowired
    public WordController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    @GetMapping(path = "/{id}")
    @Schema(description = "Найти слово по id.")
    public ResponseEntity<WordDto> getWord(@PathVariable("id")Long id){
        WordDto wordDto = WordConverter.convertToDTO(personalAccountService.findWordById(id));
        return new ResponseEntity<>(wordDto, HttpStatus.OK);
    }
    @PostMapping("/add")
    @Schema(description = "Добавить новое слово")
    public void addWord(@RequestBody WordDto wordDto){
        personalAccountService.addWord(wordDto);
    }

    @DeleteMapping("/delete/{id}")
    @Schema(description = "Удалить слово по id.")
    public void deleteWordById(@PathVariable long id){
        personalAccountService.deleteWordById(id);
    }

    @PutMapping("/put/{id}")
    @Schema(description = "Изменить слово по id.")
    public void putWordById(@PathVariable long id, @RequestBody WordDto wordDto){
        wordDto.setId(id);
        personalAccountService.updateWord(wordDto);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
