package ru.gb.wordloader.converters;

import lombok.Data;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.ArrayList;
import java.util.List;

@Data
public class VocabularyConverter {

    public Vocabulary convert(VocabularyDto vocabularyDto){
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTheme(vocabularyDto.getTheme());
        vocabulary.setPrivate(vocabulary.isPrivate());
        UserConverter userConverter = new UserConverter();
        User user = new User();
        user = userConverter.convert(vocabularyDto.getUser());
        vocabulary.setUser(user);
        List<Word> wordsList = new ArrayList<>();
        WordConverter wordConverter = new WordConverter();
        wordsList = wordConverter.convert(vocabularyDto.getWords());
        vocabulary.setWords(wordsList);
        return vocabulary;
    }
}
