package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
}
