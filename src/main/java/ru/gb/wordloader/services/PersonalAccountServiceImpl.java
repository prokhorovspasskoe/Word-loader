package ru.gb.wordloader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @Autowired
    public PersonalAccountServiceImpl(VocabularyRepository vocabularyRepository, WordRepository wordRepository, UserService userService/*, StudyPlanService studyPlanService*/) {
        this.vocabularyRepository = vocabularyRepository;
        this.wordRepository = wordRepository;
        this.userService = userService;
    }

    @Override
    public void createVocabulary(VocabularyDto vocabularyDto) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setUser(userService.getAuthenticatedUser());
        vocabulary.setTheme(vocabularyDto.getTheme());
        vocabulary.setPrivate(vocabularyDto.isPrivate());
        List<Word> wordList = WordConverter.convertFromDtoList(vocabularyDto.getWords());
        vocabulary.setWords(wordList);
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public Vocabulary getVocabularyById(long id) {
        Optional<Vocabulary> vocabulary = vocabularyRepository.findById(id);
        return vocabulary.get();
    }

    @Override
    public VocabularyDto updateVocabulary(VocabularyDto vocabularyDto) {
        Vocabulary vocabulary = VocabularyConverter.convertFromDto(vocabularyDto);

        if (vocabulary.getUser().equals(userService.getAuthenticatedUser()) && vocabulary.isPrivate()) {
            vocabulary = vocabularyRepository.save(vocabulary);
            return VocabularyConverter.convertToDto(vocabulary);
        } else {
            return null;
        }
    }

    @Override
    public void deleteVocabularyById(long id) {

        if(vocabularyRepository.findById(id).isPresent() &&
                vocabularyRepository.findById(id).get().isPrivate()){
            vocabularyRepository.deleteById(id);
        }
    }

    @Override
    public WordDto addWord(WordDto wordDto, long vocabularyId) {

        Word word = wordRepository.findByOriginal(wordDto.getOriginal());
        Vocabulary vocabulary = vocabularyRepository.findById(vocabularyId).get();
        vocabulary.getWords().add(word);
        wordRepository.save(word);
        vocabularyRepository.save(vocabulary);

        return wordDto;
    }

    @Override
    public Word findWordById(Long id) {
        Optional<Word> word = wordRepository.findById(id);
        return word.get();
    }

    @Override
    public void updateWord(WordDto wordDto) {
        Word word = WordConverter.convertFromDto(wordDto);
        wordRepository.save(word);
    }

    @Override
    public ResponseEntity<?> deleteWordById(long id, long vocabularyId) {
        if(wordRepository.findById(id).isPresent() &&
                vocabularyRepository.findById(vocabularyId).get().isPrivate()){
            Word word = wordRepository.findById(id).get();
            wordRepository.deleteById(id);
            vocabularyRepository.getReferenceById(vocabularyId).getWords().remove(word);
            return new ResponseEntity<>("The world delete.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The word being deleted does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
