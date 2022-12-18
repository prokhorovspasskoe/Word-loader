package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.Optional;

public interface PersonalAccountService {

    //Методы работы со словарём
    void createVocabulary(VocabularyDto vocabularyDto);
    Vocabulary getVocabularyById(long id);
    VocabularyDto updateVocabulary(VocabularyDto vocabularyDto);
    void deleteVocabularyById(long id);

    //Методы работы со словом
    void addWord(WordDto wordDto);
    Word findWordById(Long id);
    void updateWord(WordDto wordDto);
    void deleteWordById(long id);
}
