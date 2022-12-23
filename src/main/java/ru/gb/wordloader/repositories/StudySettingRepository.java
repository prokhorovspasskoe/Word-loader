package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.wordloader.entities.StudySetting;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.entities.Vocabulary;

public interface StudySettingRepository extends JpaRepository<StudySetting, Long> {

    StudySetting findByUserAndVocabulary(User user, Vocabulary vocabulary);
}
