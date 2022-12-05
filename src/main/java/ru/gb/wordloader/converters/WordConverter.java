package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Word;

import java.util.ArrayList;
import java.util.List;

public class WordConverter {
    public List<Word> convertFromDtoToEntity(List<WordDto> wordsDto){
        List<Word> words = new ArrayList<>();

        for (int i = 0; i < wordsDto.size(); i++) {
            Word bufferWord = new Word();
            bufferWord.setOriginal(wordsDto.get(i).getOriginal());
            bufferWord.setTranslated(wordsDto.get(i).getTranslated());
            words.add(bufferWord);
        }
        return words;
    }

    public List<WordDto> convertFromEntityToDto(List<Word> words){
        List<WordDto> wordDtoList = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            WordDto bufferWordDto = new WordDto();
            bufferWordDto.setOriginal(words.get(i).getOriginal());
            bufferWordDto.setTranslated(words.get(i).getTranslated());
            wordDtoList.add(bufferWordDto);
        }

        return wordDtoList;
    }
}
