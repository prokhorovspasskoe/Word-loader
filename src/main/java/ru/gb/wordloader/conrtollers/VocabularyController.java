package ru.gb.wordloader.conrtollers;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.services.PersonalAccountService;
import ru.gb.wordloader.services.exceptions.NotFoundException;

@RestController
@RequestMapping("/api/v1/vocabulary")
@CrossOrigin
@Schema(description = "Методы работы со словарями.")
public class VocabularyController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public VocabularyController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    @GetMapping("/get/{id}")
    @Schema(description = "Получение словаря по id.")
    public VocabularyDto getVocabularyById(@PathVariable("id") long id){
        return VocabularyConverter.convertToDto(personalAccountService.getVocabularyById(id));
    }

    @PostMapping("/add")
    @Schema(description = "Создание нового словаря.")
    @Transactional
    public void addVocabulary(@RequestBody VocabularyDto vocabularyDto){
        if(vocabularyDto != null) {
            personalAccountService.createVocabulary(vocabularyDto);
        }
    }

    @PutMapping("/put/{id}")
    @Schema(description = "Изменение ранее созданного словаря по id словаря")
    @Transactional
    public void updateVocabularyById(@PathVariable("id") long id, @RequestBody VocabularyDto vocabularyDto){
        vocabularyDto.setId(id);
        personalAccountService.updateVocabulary(vocabularyDto);
    }

    @DeleteMapping("/delete/{id}")
    @Schema(description = "Удаление словаря по id.")
    @Transactional
    public void deleteVocabularyById(@PathVariable("id") long id){
        personalAccountService.deleteVocabularyById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }
}
