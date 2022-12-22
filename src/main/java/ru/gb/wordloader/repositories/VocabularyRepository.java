package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.Vocabulary;


@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    // TODO
    //  Нам зачем нужен этот метод? Если не нужен, то убрать
    @Query(value = "select * from vocabularies where theme=?1", nativeQuery = true)
    Vocabulary getByTheme(String theme);
}
