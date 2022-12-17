package ru.gb.wordloader.services;


import ru.gb.wordloader.entities.Word;

public interface StudyProgressService {
    void saveProgress(Long studyPlanId, Word word);
}
