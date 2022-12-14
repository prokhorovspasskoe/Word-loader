package ru.gb.wordloader.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.repositories.VocabularyRepository;

import java.util.Optional;

@Service
public class VocabularyServiceImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;

    @Autowired
    public VocabularyServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public Optional<Vocabulary> findById(Long vocabularyId) {
        return vocabularyRepository.findById(vocabularyId);
    }

}
