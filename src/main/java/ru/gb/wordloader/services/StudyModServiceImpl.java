package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.StudyPlanConverter;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.*;
import ru.gb.wordloader.entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class StudyModServiceImpl implements StudyModService{

    private final StudyProgressService studyProgressService;
    private final UserService userService;
    private final StudyPlanService studyPlanService;
    private final StudySettingService studySettingService;
    private final PersonalAccountService personalAccountService;



    @Autowired
    public StudyModServiceImpl(StudyProgressService studyProgressService, UserService userService, StudyPlanService studyPlanService, StudySettingService studySettingService, PersonalAccountService personalAccountService) {
        this.studyProgressService = studyProgressService;
        this.userService = userService;
        this.studyPlanService = studyPlanService;
        this.studySettingService = studySettingService;
        this.personalAccountService = personalAccountService;
    }

    @Override
    public ResponseEntity<?> getTest(Long studyPlanId) {
        //Получаем user'a и vocabulary и находим настройки режима изучения
        StudyPlan studyPlan;
        if (studyPlanService.findById(studyPlanId).isPresent()) {
            studyPlan = studyPlanService.findById(studyPlanId).get();
        } else {
            return new ResponseEntity<>("Study plan not found", HttpStatus.BAD_REQUEST);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = studyPlan.getUser();
        if (!user.equals(userService.findByName(auth.getName()))) {
            return new ResponseEntity<>("Wrong user", HttpStatus.BAD_REQUEST);
        }

        Vocabulary vocabulary = studyPlan.getVocabulary();
        StudySetting studySetting = studySettingService.findByUserAndVocabulary(user, vocabulary);

        //Проверяем по времени с последнего теста
        LocalDateTime lastProgress = studyPlan.getLastProgress();
        int breakPeriod = studySetting.getMinBreakPeriod();
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.minusMinutes(breakPeriod).isBefore(lastProgress)) {
            return new ResponseEntity<>("The break time from the previous test has not been passed", HttpStatus.BAD_REQUEST);
        }

        //Получаем слова в словаре и исключаем изученные
        VocabularyDto vocabularyDto = VocabularyConverter.convertToDto(vocabulary);
        List<WordDto> wordsDto = vocabularyDto.getWords();
        StudyPlanDto studyPlanDto = StudyPlanConverter.convertToDto(studyPlan);
        List<StudyWordDto> learnedWords = studyPlanDto.getStudyWords();
        int wordsInTest = studySetting.getWordsInTest();
        int correctAnswerRequired = studySetting.getCorrectAttemptsRequired();
        for (int i = 0; i < learnedWords.size(); i++) {
            if (learnedWords.get(i).getCorrectAnswers() >= correctAnswerRequired) {
                WordDto deleteWordDto = WordConverter.convertFromStudyWordDto(learnedWords.get(i));
                wordsDto.remove(deleteWordDto);
                }
            }

        //Возвращаем если словарь уже выучен
        if (wordsDto.size() == 0) {
            return new ResponseEntity<>("The dictionary is already learned", HttpStatus.BAD_REQUEST);
        }

            //Создаем список слов
            List<WordDto> testWordDtos = new ArrayList<>();
            if (wordsInTest > wordsDto.size()) {
                Collections.shuffle(wordsDto);
                TestDto testDto = TestDto.builder()
                        .studyPlan_id(studyPlanId)
                        .words(wordsDto)
                        .build();
                return new ResponseEntity<>(testDto, HttpStatus.OK);
            } else {
                Collections.shuffle(wordsDto);
                for (int i = 0; i < wordsInTest; i++) {
                    testWordDtos.add(wordsDto.get(i));
                }
                TestDto testDto = TestDto.builder()
                        .studyPlan_id(studyPlanId)
                        .words(wordsDto)
                        .build();
                return new ResponseEntity<>(testDto, HttpStatus.OK);
            }
        }

    @Override
    public String wordCheck(UserWordDto userWordDto) {

        //Получаем слово
        Word word = personalAccountService.findWordById(userWordDto.getWord_id());

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
    public ResponseEntity<?> takeVocabularyToStudy(Long vocabularyId) {
        //Получаем словарь, который хотим взять на изучение
        Vocabulary vocabulary = personalAccountService.getVocabularyById(vocabularyId);

        if (vocabulary == null){
            return new ResponseEntity<>("Vocabulary not found", HttpStatus.BAD_REQUEST);
        }

        //Получаем пользователя, под которым авторизовались
        User user = userService.getAuthenticatedUser();

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
        return new ResponseEntity<>("The dictionary is taken for study.", HttpStatus.OK);
    }

}
