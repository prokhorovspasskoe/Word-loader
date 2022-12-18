package ru.gb.wordloader.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.StudyPlan;

@Repository
public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
}
