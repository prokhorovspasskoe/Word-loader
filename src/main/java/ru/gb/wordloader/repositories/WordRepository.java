package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
}
