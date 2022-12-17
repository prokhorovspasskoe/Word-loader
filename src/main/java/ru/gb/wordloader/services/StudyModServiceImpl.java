package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.dto.TestDto;
import ru.gb.wordloader.dto.UserWordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.VocabularyRepository;
import ru.gb.wordloader.repositories.WordRepository;


@Service
public class StudyModServiceImpl implements StudyModService{
    private final VocabularyRepository vocabularyRepository;
    private final WordRepository wordRepository;
    private final StudyProgressService studyProgressService;


    @Autowired
    public StudyModServiceImpl(VocabularyRepository vocabularyRepository, WordRepository wordRepository, StudyProgressService studyProgressService) {
        this.vocabularyRepository = vocabularyRepository;
        this.wordRepository = wordRepository;
        this.studyProgressService = studyProgressService;
    }

    @Override
    public TestDto getTest(Long studyPlanId) {
        return null;
    }

    @Override
    public String wordCheck(UserWordDto userWordDto) {

        Word word = wordRepository.findById(userWordDto.getWord_id()).orElseThrow(); //Получаем слово
        String userTypedValue = userWordDto.getUserTypedValue().trim(); //Значение, которое ввёл пользователь, обрезаем пробелы
        if (word.getTranslated().equalsIgnoreCase(userTypedValue)) { //При проверке игнорим регистр
            studyProgressService.saveProgress(userWordDto.getStudyPlan_id(), word); //Если корректный ввод, сохраняем прогресс в БД
            return "CORRECT";
        } else {
            return "WRONG";
        }

    }

    /**
     * Взять словарь на изучение
     * */
    @Override
    public void takeVocabularyToLearning(Long vocabularyId) {
        Vocabulary vocabulary = vocabularyRepository.findById(vocabularyId).orElseThrow();


    }

}
