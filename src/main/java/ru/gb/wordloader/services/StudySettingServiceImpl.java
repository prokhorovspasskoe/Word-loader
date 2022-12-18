package ru.gb.wordloader.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.StudySetting;
import ru.gb.wordloader.repositories.StudySettingRepository;

@Service
public class StudySettingServiceImpl implements StudySettingService {

    private final StudySettingRepository studySettingRepository;

    @Autowired
    public StudySettingServiceImpl(StudySettingRepository studySettingRepository) {
        this.studySettingRepository = studySettingRepository;
    }

    @Override
    public void save(StudySetting studySetting) {
        studySettingRepository.save(studySetting);
    }

    @Override
    public StudySetting getSettingsUserVocabulary(long userId, long vocabularyId) {
        return studySettingRepository.getByUserVocabulary(userId, vocabularyId);
    }
}
