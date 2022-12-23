package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.WordRepository;

import java.util.Optional;

@Service
public class WordServiceImpl implements WordService{

    private final WordRepository wordRepository;

    @Autowired
    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Optional<Word> findById(Long wordId) {
        return wordRepository.findById(wordId);
    }
}
