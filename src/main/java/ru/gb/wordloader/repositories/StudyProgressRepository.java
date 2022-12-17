package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.StudyProgress;

public interface StudyProgressRepository extends JpaRepository<StudyProgress, Long> {
}
