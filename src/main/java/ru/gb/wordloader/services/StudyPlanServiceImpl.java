package ru.gb.wordloader.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.StudyPlan;
import ru.gb.wordloader.repositories.StudyPlanRepository;

@Service
public class StudyPlanServiceImpl implements StudyPlanService{

    private final StudyPlanRepository studyPlanRepository;

    @Autowired
    public StudyPlanServiceImpl(StudyPlanRepository studyPlanRepository) {
        this.studyPlanRepository = studyPlanRepository;
    }

    @Override
    public void save(StudyPlan studyPlan) {
        studyPlanRepository.save(studyPlan);
    }
}
