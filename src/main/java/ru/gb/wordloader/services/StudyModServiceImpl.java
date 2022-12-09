package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.VocabularyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyModServiceImpl implements StudyModService{
    private final VocabularyRepository vocabularyRepository;

    @Autowired
    public StudyModServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public List<WordDto> initialize(long vocabularyId, int wordsInTest) {
        Vocabulary vocabulary = vocabularyRepository.getReferenceById(vocabularyId);
        List<Word> testWordsList = new ArrayList<>();

        for (int i = 0; i < wordsInTest; i++) {
            testWordsList.add(vocabulary.getWords().get(i));
        }

        WordConverter wordConverter = new WordConverter();

        return  wordConverter.convertFromEntityToDto(testWordsList);
    }
}
