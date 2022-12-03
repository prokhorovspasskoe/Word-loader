package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;

public interface PersonalAccountService {
    void createVocabulary(VocabularyDto vocabularyDto);
    VocabularyDto getVocabularyById(long id);
    VocabularyDto updateVocabulary(VocabularyDto vocabularyDto);
    void deleteVocabularyById(long id);
}
