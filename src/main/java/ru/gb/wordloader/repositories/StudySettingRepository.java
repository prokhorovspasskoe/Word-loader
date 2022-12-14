package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.StudySetting;

public interface StudySettingRepository extends JpaRepository<StudySetting, Long> {

}
