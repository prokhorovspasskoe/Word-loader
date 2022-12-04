package ru.gb.wordloader.conrtollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.services.WordService;
import ru.gb.wordloader.services.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/word")
public class WordController {

    private WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<WordDto> getWord(@PathVariable("id")Long id){
        Word word = wordService.findById(id).orElseThrow(NotFoundException::new);
        WordDto wordDto = toDTO(word);
        return new ResponseEntity<>(wordDto, HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    private WordDto toDTO(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .original(word.getOriginal())
                .translated(word.getTranslated())
                .build();
    }
}
