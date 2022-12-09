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
import java.util.stream.Collectors;

@Data
public class VocabularyConverter {

    public static VocabularyDto convertToDto(Vocabulary vocabulary) {
        return  VocabularyDto.builder()
                .id(vocabulary.getId())
                .theme(vocabulary.getTheme())
                .isPrivate(vocabulary.isPrivate())
                .words( WordConverter.convertToDtoList(vocabulary.getWords()))
                .build();
    }

    public static Vocabulary convertFromDto(VocabularyDto vocabularyDto) {
        return  Vocabulary.builder()
                .id(vocabularyDto.getId())
                .theme(vocabularyDto.getTheme())
                .isPrivate(vocabularyDto.isPrivate())
                .words( WordConverter.convertFromDtoList(vocabularyDto.getWords()))
                .build();
    }

    public static List<VocabularyDto> convertToDtoList(List<Vocabulary> vocabularies) {
        return vocabularies.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    public static List<Vocabulary> convertFromDtoList(List<VocabularyDto> vocabularyDtos) {
        return vocabularyDtos.stream().map(p -> convertFromDto(p)).collect(Collectors.toList());
    }

    /*
    public Vocabulary convertFromDtoToEntity(VocabularyDto vocabularyDto){
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTheme(vocabularyDto.getTheme());
        vocabulary.setPrivate(vocabulary.isPrivate());
        UserConverter userConverter = new UserConverter();
        User user = new User();
        user = userConverter.convertFromDtoToEntity(vocabularyDto.getUser());
        vocabulary.setUser(user);
        List<Word> wordsList = new ArrayList<>();
        WordConverter wordConverter = new WordConverter();
        wordsList = wordConverter.convertFromDtoToEntity(vocabularyDto.getWords());
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
    */
}
