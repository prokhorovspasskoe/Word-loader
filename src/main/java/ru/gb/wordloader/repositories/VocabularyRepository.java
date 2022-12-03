package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

}
