package ru.gb.wordloader.conrtollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.*;
import ru.gb.wordloader.services.StudyModService;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/study")
public class StudyModController {

    private final StudyModService studyModService;

    @Autowired
    public StudyModController(StudyModService studyModService) {
        this.studyModService = studyModService;
    }

    @GetMapping("/test/{studyPlan_id}")
    public TestDto getTest(@PathVariable("studyPlan_id") Long studyPlanId) {
        return studyModService.getTest(studyPlanId);
    }

    @PostMapping("/wordCheck")
    public ResponseEntity<?> wordCheck(@RequestBody UserWordDto userWordDto) {

        String checkResult = studyModService.wordCheck(userWordDto); //Пока в реализации вернём CORRECT или WRONG

        return new ResponseEntity<>(checkResult, HttpStatus.OK);
    }

    @PostMapping("/takeVocabulary/{studyPlan_id}")
    public void takeVocabularyToLearning(@PathVariable("studyPlan_id") Long studyPlanId) {
        studyModService.takeVocabularyToLearning(studyPlanId);
    }
}
