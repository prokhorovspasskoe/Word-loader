package ru.gb.wordloader.services;


import ru.gb.wordloader.entities.StudySetting;

public interface StudySettingService {
    void save(StudySetting studySetting);
    StudySetting getSettingsUserVocabulary(long userId, long vocabularyId);
}
