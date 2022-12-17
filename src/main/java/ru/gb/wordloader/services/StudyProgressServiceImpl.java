package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.StudyPlan;
import ru.gb.wordloader.entities.StudyProgress;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.StudyPlanRepository;
import ru.gb.wordloader.repositories.StudyProgressRepository;

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
        StudyProgress studyProgress = StudyProgress.builder()
                .studyPlan(studyPlan)
                .word(word)
                .build();

        studyProgressRepository.save(studyProgress);
    }
}
