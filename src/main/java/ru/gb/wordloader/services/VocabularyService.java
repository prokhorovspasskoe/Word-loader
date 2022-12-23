package ru.gb.wordloader.services;


import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.Optional;

public interface VocabularyService {
    Optional<Vocabulary> findById(Long vocabularyId);
}
