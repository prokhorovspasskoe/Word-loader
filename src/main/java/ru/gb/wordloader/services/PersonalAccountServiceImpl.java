package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.Vocabulary;
import ru.gb.wordloader.entities.Word;
import ru.gb.wordloader.repositories.VocabularyRepository;
import ru.gb.wordloader.repositories.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalAccountServiceImpl implements PersonalAccountService{
    private final VocabularyRepository vocabularyRepository;
    private final WordRepository wordRepository;

    @Autowired
    public PersonalAccountServiceImpl(VocabularyRepository vocabularyRepository, WordRepository wordRepository) {
        this.vocabularyRepository = vocabularyRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public void createVocabulary(VocabularyDto vocabularyDto) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTheme(vocabularyDto.getTheme());
        vocabulary.setPrivate(vocabularyDto.isPrivate());
        WordConverter wordConverter = new WordConverter();
        List<Word> wordList = WordConverter.convertFromDtoToEntity(vocabularyDto.getWords());
        vocabulary.setWords(wordList);
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public VocabularyDto getVocabularyById(long id) {
        Vocabulary vocabulary = vocabularyRepository.getReferenceById(id);
        return VocabularyConverter.convertToDto(vocabulary);
    }

    @Override
    public VocabularyDto updateVocabulary(VocabularyDto vocabularyDto) {
        Vocabulary vocabulary = VocabularyConverter.convertFromDto(vocabularyDto);
        vocabulary = vocabularyRepository.save(vocabulary);
        return VocabularyConverter.convertToDto(vocabulary);
    }
    @Override
    public void deleteVocabularyById(long id) {
        vocabularyRepository.deleteById(id);
    }

    @Override
    public void addWord(WordDto wordDto) {
        WordConverter wordConverter = new WordConverter();
        Word word = wordConverter.convertDtoToEntity(wordDto);
        wordRepository.save(word);
    }
    @Override
    public WordDto findWordById(Long id) {
        Optional<Word> word = wordRepository.findById(id);
        WordConverter wordConverter = new WordConverter();
        return wordConverter.convertEntityToDTO(word.get());
    }

    @Override
    public void updateWord(WordDto wordDto) {
        WordConverter wordConverter = new WordConverter();
        Word word = wordConverter.convertDtoToEntity(wordDto);
        wordRepository.save(word);
    }

    @Override
    public void deleteWordById(long id) {
        wordRepository.deleteById(id);
    }
}
