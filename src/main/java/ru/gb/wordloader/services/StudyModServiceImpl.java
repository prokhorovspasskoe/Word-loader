package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.dto.TestDto;
import ru.gb.wordloader.dto.UserWordDto;
import ru.gb.wordloader.repositories.VocabularyRepository;


@Service
public class StudyModServiceImpl implements StudyModService{
    private final VocabularyRepository vocabularyRepository;


    @Autowired
    public StudyModServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public TestDto getTest(Long studyPlanId) {
        return null;
    }

    @Override
    public String wordCheck(UserWordDto userWordDto) {
        return null;
    }

    @Override
    public void takeVocabularyToLearning(Long studyPlanId) {

    }

}
