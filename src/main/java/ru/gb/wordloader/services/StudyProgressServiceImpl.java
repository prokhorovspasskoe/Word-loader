package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.StudyPlan;
import ru.gb.wordloader.entities.StudyProgress;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.StudyPlanRepository;
import ru.gb.wordloader.repositories.StudyProgressRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class StudyProgressServiceImpl implements StudyProgressService{

    private final StudyPlanRepository studyPlanRepository;
    private final StudyProgressRepository studyProgressRepository;

    @Autowired
    public StudyProgressServiceImpl(StudyPlanRepository studyPlanRepository, StudyProgressRepository studyProgressRepository) {
        this.studyPlanRepository = studyPlanRepository;
        this.studyProgressRepository = studyProgressRepository;
    }

    @Override
    public void saveProgress(Long studyPlanId, Word word) {

        StudyPlan studyPlan = studyPlanRepository.findById(studyPlanId).orElseThrow();

        //Сохраняем прогресс изучения
        StudyProgress studyProgress = StudyProgress.builder()
                .studyPlan(studyPlan)
                .word(word)
                .build();
        studyProgressRepository.save(studyProgress);

        //Сохраняем дату и время последнего теста
        //Сюда не попадёт, если пользователь ни разу в тесте не ответит правильно.
        //Будем считать такой тест "пустым" и не будем засчитывать его. Важно, чтобы
        //между правильными ответами был временной промежуток для лучшего усвоения
        //изучаемых слов
        studyPlan.setLastProgress(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        studyPlanRepository.save(studyPlan);
    }
}
