package ru.gb.wordloader.services;


import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.List;
import java.util.Optional;

public interface VocabularyService {
    Optional<Vocabulary> findById(Long vocabularyId);
    List<VocabularyDto> findAllPublicVocabularies();
}
