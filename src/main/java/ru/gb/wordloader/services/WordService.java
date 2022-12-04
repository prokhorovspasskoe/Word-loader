package ru.gb.wordloader.services;

import org.springframework.stereotype.Service;
import ru.gb.wordloader.entities.Word;

import java.util.Optional;


public interface WordService {
    Optional<Word> findById(Long id);


}
