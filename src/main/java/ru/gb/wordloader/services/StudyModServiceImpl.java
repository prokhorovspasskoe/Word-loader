package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.repositories.VocabularyRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudyModServiceImpl implements StudyModService{
    private final VocabularyRepository vocabularyRepository;
    private List<WordDto> wordDtoList;
    private int minBreakPeriod;
    private int correctAttemptsRequired;
    private int wordsInTest;
    private int counter;

    @Autowired
    public StudyModServiceImpl(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

    @Override
    public List<WordDto> initialize(String theme, int minBreakPeriod, int correctAttemptsRequired, int wordsInTest) {
        this.correctAttemptsRequired = correctAttemptsRequired;
        this.wordsInTest = wordsInTest;
        this.minBreakPeriod = minBreakPeriod;

        Set<Integer> integers = new HashSet<>();

        Vocabulary vocabulary = vocabularyRepository.getByTheme(theme);
        wordDtoList = new ArrayList<>();

        for (int i = 0; i < wordsInTest; i++) {
            int index = generateIndex();

            if(integers.add(index)) {
                WordDto wordDto = WordConverter.convertToDTO(vocabulary.getWords().get(index));
                wordDtoList.add(wordDto);
            }else {
                i--;
                continue;
            }

            WordDto wordDto = WordConverter.convertToDTO(vocabulary.getWords().get(index));
            wordDtoList.add(wordDto);
        }

        return wordDtoList;
    }

    private int generateIndex(){
        return (int) (Math.random() * wordsInTest);
    }

}
