package ru.gb.wordloader.services;

import ru.gb.wordloader.entities.Word;

import java.util.Optional;

public interface WordService {
    Optional<Word> findById(Long wordId);

}
