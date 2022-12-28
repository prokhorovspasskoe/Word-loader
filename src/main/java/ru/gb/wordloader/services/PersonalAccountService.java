package ru.gb.wordloader.services;

import org.springframework.http.ResponseEntity;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

public interface PersonalAccountService {
    //Методы работы со словарями
    void createVocabulary(VocabularyDto vocabularyDto);
    Vocabulary getVocabularyById(long id);
    VocabularyDto updateVocabulary(VocabularyDto vocabularyDto);
    void deleteVocabularyById(long id);

    //Методы работы со словами
    WordDto addWord(WordDto wordDto, long vocabularyId);
    Word findWordById(Long id);
    void updateWord(WordDto wordDto);
    ResponseEntity<?> deleteWordById(long id, long vocabularyId);
}
