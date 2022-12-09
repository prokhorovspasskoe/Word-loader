package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Word;

import java.util.List;
import java.util.stream.Collectors;

public class WordConverter {

    public static WordDto convertToDto(Word word) {
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
        return words.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }

    public static List<Word> convertFromDtoList(List<WordDto> wordDtos) {
        return wordDtos.stream().map(p -> convertFromDto(p)).collect(Collectors.toList());
    }

    /*
    public List<Word> convertFromDtoToEntity(List<WordDto> wordsDto){
        List<Word> words = new ArrayList<>();
        Word bufferWord = new Word();
        WordDto bufferWordDto = new WordDto();

        for (int i = 0; i < wordsDto.size(); i++) {
            bufferWordDto = wordsDto.get(i);
            bufferWord.setOriginal(bufferWordDto.getOriginal());
            bufferWord.setTranslated(bufferWordDto.getTranslated());
            words.add(bufferWord);
        }
        return words;
    }

    public List<WordDto> convertFromEntityToDto(List<Word> words){
        List<WordDto> wordDtoList = new ArrayList<>();
        Word bufferWord = new Word();
        WordDto bufferWordDto = new WordDto();

        for (int i = 0; i < words.size(); i++) {
            bufferWord = words.get(i);
            bufferWordDto.setOriginal(bufferWord.getOriginal());
            bufferWordDto.setTranslated(bufferWord.getTranslated());
            wordDtoList.add(bufferWordDto);
        }

        return wordDtoList;
    }
    */
}
