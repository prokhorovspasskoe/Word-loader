package ru.gb.wordloader.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.services.PersonalAccountService;

@RestController
@RequestMapping("/api/v1/vocabulary")
@CrossOrigin
public class VocabularyController {
    private final PersonalAccountService personalAccountService;

    @Autowired
    public VocabularyController(PersonalAccountService personalAccountService) {
        this.personalAccountService = personalAccountService;
    }

    @GetMapping("/get/{id}")
    public VocabularyDto getVocabularyById(@PathVariable("id") long id){
        return VocabularyConverter.convertToDto(personalAccountService.getVocabularyById(id));
    }

    @PostMapping("/add")
    public void addVocabulary(@RequestBody VocabularyDto vocabularyDto){
        personalAccountService.createVocabulary(vocabularyDto);
    }

    @PutMapping("/put/{id}")
    public void updateVocabularyById(@PathVariable("id") long id, @RequestBody VocabularyDto vocabularyDto){
        vocabularyDto.setId(id);
        personalAccountService.updateVocabulary(vocabularyDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVocabularyById(@PathVariable("id") long id){
        personalAccountService.deleteVocabularyById(id);
    }
}
