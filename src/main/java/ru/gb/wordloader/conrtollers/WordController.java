package ru.gb.wordloader.conrtollers;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public ResponseEntity<?> getWord(@PathVariable("id")Long id){
        WordDto wordDto = WordConverter.convertToDTO(personalAccountService.findWordById(id));
        if(wordDto != null){
            return new ResponseEntity<>(wordDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The word is not in the dictionary", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    @Schema(description = "Добавить новое слово в словарь")
    @Transactional
    public void addWordToVocabulary(@RequestBody WordDto wordDto, long vocabularyId){
        if(wordDto != null){
            personalAccountService.addWord(wordDto, vocabularyId);
        }
    }

    @DeleteMapping("/delete/{id}/{vocabularyId}")
    @Schema(description = "Удалить слово по id.")
    @Transactional
    public void deleteWordById(@PathVariable long id, long vocabularyId){
        personalAccountService.deleteWordById(id, vocabularyId);
    }

    @PutMapping("/put/{id}")
    @Schema(description = "Изменить слово по id.")
    @Transactional
    public void putWordById(@PathVariable long id, @RequestBody WordDto wordDto){
        wordDto.setId(id);
        personalAccountService.updateWord(wordDto);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
