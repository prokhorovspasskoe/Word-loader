package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.Vocabulary;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    @Query(value = "select * from vocabularies where theme=?1", nativeQuery = true)
    Vocabulary getByTheme(String theme);
}

