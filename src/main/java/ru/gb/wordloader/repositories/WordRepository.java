package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "select * from words where original = ?1", nativeQuery = true)
    Word findByOriginal(String original);
}
