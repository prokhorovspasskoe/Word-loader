package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.repositories.VocabularyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyModServiceImpl implements StudyModService{
    private final VocabularyRepository vocabularyRepository;
    private List<WordDto> wordDtoList;
    private int minBreakPeriod;
    private int correctAttemptsRequired;
    private int wordInTest;

    @Autowired
    public StudyModServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public String initialize(String theme, int minBreakPeriod, int correctAttemptsRequired, int wordsInTest) {
        this.correctAttemptsRequired = correctAttemptsRequired;
        this.wordInTest = wordsInTest;
        this.minBreakPeriod = minBreakPeriod;

        Vocabulary vocabulary = vocabularyRepository.getByTheme(theme);
        wordDtoList = new ArrayList<>();

        //В дальнейшем реализую рандомный выбор слов из словаря
        for (int i = 0; i < wordsInTest; i++) {
           WordDto wordDto = WordConverter.convertToDTO(vocabulary.getWords().get(i));
           wordDtoList.add(wordDto);
        }

        return wordDtoList.get(0).getOriginal();
    }
}
