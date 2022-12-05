package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.wordloader.converters.UserConverter;
import ru.gb.wordloader.converters.VocabularyConverter;
import ru.gb.wordloader.converters.WordConverter;
import ru.gb.wordloader.dto.VocabularyDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.User;
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
        User user = new User();
        UserConverter userConverter = new UserConverter();
        user = userConverter.convertFromDtoToEntity(vocabularyDto.getUser());
        vocabulary.setUser(user);
        WordConverter wordConverter = new WordConverter();
        List<Word> wordList = wordConverter.convertFromDtoToEntity(vocabularyDto.getWords());
        vocabulary.setWords(wordList);
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public VocabularyDto getVocabularyById(long id) {
        Vocabulary vocabulary = vocabularyRepository.getReferenceById(id);
        VocabularyConverter vocabularyConverter = new VocabularyConverter();
        return vocabularyConverter.convertFromEntityToDto(vocabulary);
    }

    @Override
    public VocabularyDto updateVocabulary(VocabularyDto vocabularyDto) {
        VocabularyConverter vocabularyConverter = new VocabularyConverter();
        Vocabulary vocabulary = vocabularyConverter.convertFromDtoToEntity(vocabularyDto);
        vocabulary = vocabularyRepository.save(vocabulary);
        return vocabularyConverter.convertFromEntityToDto(vocabulary);
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
