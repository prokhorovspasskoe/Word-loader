package ru.gb.wordloader.converters;

import lombok.Data;
import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.ArrayList;
import java.util.List;

@Data
public class VocabularyConverter {

    public Vocabulary convertFromDtoToEntity(VocabularyDto vocabularyDto){
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTheme(vocabularyDto.getTheme());
        vocabulary.setPrivate(vocabulary.isPrivate());
        UserConverter userConverter = new UserConverter();
        User user = userConverter.convertFromDtoToEntity(vocabularyDto.getUser());
        vocabulary.setUser(user);
        WordConverter wordConverter = new WordConverter();
        List<Word> wordsList = wordConverter.convertFromDtoToEntity(vocabularyDto.getWords());
        vocabulary.setWords(wordsList);
        return vocabulary;
    }

    public VocabularyDto convertFromEntityToDto(Vocabulary vocabulary){
        VocabularyDto vocabularyDto = new VocabularyDto();
        vocabularyDto.setTheme(vocabulary.getTheme());
        vocabularyDto.setPrivate(vocabulary.isPrivate());
        UserConverter userConverter = new UserConverter();
        UserDto userDto = userConverter.convertFromEntityToDto(vocabulary.getUser());
        vocabularyDto.setUser(userDto);
        WordConverter wordConverter = new WordConverter();
        List<WordDto> wordDtoList = wordConverter.convertFromEntityToDto(vocabulary.getWords());
        vocabularyDto.setWords(wordDtoList);
        return vocabularyDto;
    }
}
