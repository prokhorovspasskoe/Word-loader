package ru.gb.wordloader.conrtollers;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.*;
import ru.gb.wordloader.services.StudyModService;
import ru.gb.wordloader.services.exceptions.NotFoundException;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/study")
@Schema(description = "Режим изучения")
public class StudyModController {

    private final StudyModService studyModService;

    @Autowired
    public StudyModController(StudyModService studyModService) {
        this.studyModService = studyModService;
    }

    @GetMapping("/test/{studyPlan_id}")
    public ResponseEntity<?> getTest(@PathVariable("studyPlan_id") Long studyPlanId) {
        return studyModService.getTest(studyPlanId);
    }

    @PostMapping("/wordCheck")
    @Schema(description = "Проверка введённого пользователем ответа.")
    @Transactional
    public ResponseEntity<?> wordCheck(@RequestBody UserWordDto userWordDto) {
        try {
            String checkResult = studyModService.wordCheck(userWordDto); //Пока в реализации вернём CORRECT или WRONG

            return new ResponseEntity<>(checkResult, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.OK);
        }
    }

    @PostMapping("/takeVocabulary/{vocabulary_id}")
    @Schema(description = "Взять словарь на изучение.")
    @Transactional
    public ResponseEntity<?> takeVocabularyToLearning(@PathVariable("vocabulary_id") Long vocabularyId) {
        try {
            studyModService.takeVocabularyToStudy(vocabularyId);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("ERROR", HttpStatus.OK);
        }
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {

        return new ResponseEntity<>("Incorrect request", HttpStatus.BAD_REQUEST);
    }
}

