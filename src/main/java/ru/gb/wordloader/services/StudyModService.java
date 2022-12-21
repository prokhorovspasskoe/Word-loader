package ru.gb.wordloader.services;

import org.springframework.http.ResponseEntity;
import ru.gb.wordloader.dto.TestDto;
import ru.gb.wordloader.dto.UserWordDto;

public interface StudyModService {

    ResponseEntity<?> getTest(Long studyPlanId);

    String wordCheck(UserWordDto userWordDto);

    ResponseEntity<?> takeVocabularyToStudy(Long vocabularyId);

}
