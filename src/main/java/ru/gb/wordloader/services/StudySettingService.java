package ru.gb.wordloader.services;


import ru.gb.wordloader.entities.StudySetting;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.entities.Vocabulary;

public interface StudySettingService {
    void save(StudySetting studySetting);
    StudySetting findByUserAndVocabulary(User user, Vocabulary vocabulary);
}
