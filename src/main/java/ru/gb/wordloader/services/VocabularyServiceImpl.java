package ru.gb.wordloader.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.repositories.VocabularyRepository;

import java.util.List;
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

    @Override
    public List<VocabularyDto> findAllPublicVocabularies() {
        return VocabularyConverter.convertToDtoList(vocabularyRepository.getAllByIsPrivateFalse());
    }

}
