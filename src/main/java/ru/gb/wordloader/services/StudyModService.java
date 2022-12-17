package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.TestDto;
import ru.gb.wordloader.dto.UserWordDto;

public interface StudyModService {

    TestDto getTest(Long studyPlanId);

    String wordCheck(UserWordDto userWordDto);

    void takeVocabularyToLearning(Long vocabularyId);

}
