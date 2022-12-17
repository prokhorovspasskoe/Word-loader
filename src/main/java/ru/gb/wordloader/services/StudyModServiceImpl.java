package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.dto.TestDto;
import ru.gb.wordloader.dto.UserWordDto;
import ru.gb.wordloader.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;


@Service
public class StudyModServiceImpl implements StudyModService{

    private final VocabularyService vocabularyService;
    private final WordService wordService;
    private final StudyProgressService studyProgressService;
    private final UserService userService;
    private final StudyPlanService studyPlanService;
    private final StudySettingService studySettingService;



    @Autowired
    public StudyModServiceImpl(VocabularyService vocabularyService, WordService wordService, StudyProgressService studyProgressService, UserService userService, StudyPlanService studyPlanService, StudySettingService studySettingService) {
        this.vocabularyService = vocabularyService;
        this.wordService = wordService;
        this.studyProgressService = studyProgressService;
        this.userService = userService;
        this.studyPlanService = studyPlanService;
        this.studySettingService = studySettingService;
    }

    @Override
    public TestDto getTest(Long studyPlanId) {
        return null;
    }


    //TODO
    // Реализовать доработку, отлавливающую исключения.

    @Override
    public String wordCheck(UserWordDto userWordDto) {

        //Получаем слово
        Word word = wordService.findById(userWordDto.getWord_id()).orElseThrow();

        //Значение, которое ввёл пользователь, обрезаем пробелы
        String userTypedValue = userWordDto.getUserTypedValue().trim();

        if (word.getTranslated().equalsIgnoreCase(userTypedValue)) { //При проверке игнорим регистр

            //Если корректный ввод, сохраняем прогресс в БД
            studyProgressService.saveProgress(userWordDto.getStudyPlan_id(), word);

            return "CORRECT";
        } else {
            return "WRONG";
        }
    }

    // TODO
    //  На таблицы study_plans и study_settings в БД повесил constraint'ы. Сделать :
    //  1. Реализовать доработку, отлавливающую исключения.
    //  2. Добавление обеих записей должно быть в одной транзакции

    @Override
    public void takeVocabularyToStudy(Long vocabularyId) {
        //Получаем словарь, который хотим взять на изучение
        Vocabulary vocabulary = vocabularyService.findById(vocabularyId).orElseThrow();

        //Получаем пользователя, под которым авторизовались
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByName(auth.getName());

        //Прописываем словарь в изучаемые
        StudyPlan studyPlan = StudyPlan.builder()
                .vocabulary(vocabulary)
                .user(user)
                .lastProgress(LocalDateTime.of(LocalDate.now(), LocalTime.now()))
                .build();
        studyPlanService.save(studyPlan);

        //Создаём и сохраняем настройку режима изучения словаря.
        //Параметры задаём по-умолчанию. Потом их можно куда-нибудь в настройки закинуть
        StudySetting studySetting = StudySetting.builder()
                .vocabulary(vocabulary)
                .user(user)
                .correctAttemptsRequired(10)  //Значения по умолчанию
                .wordsInTest(20)
                .minBreakPeriod(15)
                .build();
        studySettingService.save(studySetting);
    }

}
