package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.wordloader.entities.StudySetting;
import ru.gb.wordloader.entities.Vocabulary;

public interface StudySettingRepository extends JpaRepository<StudySetting, Long> {

    @Query(value = "select * from study_settings where user_id = :userId and vocabulary_id = :vocabularyId", nativeQuery = true)
    StudySetting getByUserVocabulary(long userId, long vocabularyId);

}
