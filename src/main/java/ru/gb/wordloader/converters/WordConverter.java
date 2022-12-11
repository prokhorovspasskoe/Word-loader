package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordConverter {

    public static  WordDto convertToDTO(Word word) {
        return WordDto.builder()
                .id(word.getId())
                .original(word.getOriginal())
                .translated(word.getTranslated())
                .build();
    }

    public static Word convertFromDto(WordDto wordDto) {
        return Word.builder()
                .id(wordDto.getId())
                .original(wordDto.getOriginal())
                .translated(wordDto.getTranslated())
                .build();
    }

    public static List<WordDto> convertToDtoList(List<Word> words) {
        return words.stream().map(p -> convertToDTO(p)).collect(Collectors.toList());
    }

    public static List<Word> convertFromDtoList(List<WordDto> wordDtos) {
        return wordDtos.stream().map(p -> convertFromDto(p)).collect(Collectors.toList());
    }

}
