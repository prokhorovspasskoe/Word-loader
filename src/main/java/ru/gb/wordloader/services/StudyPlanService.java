package ru.gb.wordloader.services;


import ru.gb.wordloader.entities.StudyPlan;

import java.util.Optional;

public interface StudyPlanService {
    void save(StudyPlan studyPlan);
    Optional<StudyPlan> findById(long id);
}
