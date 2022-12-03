package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Word;

import java.util.ArrayList;
import java.util.List;

public class WordConverter {
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
}
