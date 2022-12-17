package ru.gb.wordloader.conrtollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.wordloader.dto.*;
import ru.gb.wordloader.services.StudyModService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/study")
public class StudyModController {

    private final StudyModService studyModService;
    //private List<WordDto> repeatList;

    public StudyModController(StudyModService studyModService) {
        this.studyModService = studyModService;
    }

    /*
    @PostMapping("/init")
    public List<WordDto> initialize(StudySettingDto settingDto){
        repeatList = studyModService.initialize(settingDto.getTheme(), settingDto.getMinBreakPeriod(),
                settingDto.getCorrectAttemptsRequired(), settingDto.getWordsInTest());
        return repeatList;
    }
    @GetMapping("/repeat")
    public List<WordDto>repeatTest(){
        return this.repeatList;
    }
    */

    @GetMapping("/test/{studyPlan_id}")
    public TestDto getTest(@PathVariable("id") long id) {
        return null;
    }

    @PostMapping("/wordCheck")
    public ResponseEntity<?> wordCheck(@RequestBody UserWordDto userWordDto) {
        return null;
    }

    @PostMapping("/takeVocabulary/{studyPlanId}")
    public void takeVocabularyToLearning(@PathVariable("studyPlanId") long studyPlanId) {

    }
}
