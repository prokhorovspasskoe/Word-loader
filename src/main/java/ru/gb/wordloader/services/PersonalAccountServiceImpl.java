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

        // TODO
        //   Добавить проверку, что публичный словарь нельзя изменять, если он взят
        //   кем-нибудь на изучение
        //Проверяем, что текущий авторизованный пользователь является автором словаря
        if (vocabulary.getUser().equals(userService.getAuthenticatedUser())) {
            vocabulary = vocabularyRepository.save(vocabulary);
            return VocabularyConverter.convertToDto(vocabulary);
        } else {
            return null;
        }
    }

    // TODO
    //   Добавить проверку, что словарь не взят никем на изучение,
    //   иначе удалять нельзя
    @Override
    public void deleteVocabularyById(long id) {
        if(vocabularyRepository.findById(id).isPresent()){
            vocabularyRepository.deleteById(id);
        }
    }

    @Override
    public void addWord(WordDto wordDto, long vocabularyId) {
        // TODO
        //   Я думаю, в этом месте нужно изменить логику. У нас на фронте пользователем будет введено слово и его перевод.
        //   И всё это будет на форме определённого словаря. То есть нам придёт wordDto скорее всего не существующего в нашей БД слова
        //   и без ID. Тут нужно по wordDto.original попробовать найти слово в БД (в БД ограничение на уникальность по original).
        //   Если есть, то вернуть его и прописать в словарь. А если нет, то сохранить и тоже вернуть.
        //   И после всего этого уже сохранить в словарь.
        Word word = WordConverter.convertFromDto(wordDto);
        //   ---------------------------------------------------

        Vocabulary vocabulary = vocabularyRepository.findById(vocabularyId).get();
        vocabulary.getWords().add(word);
        vocabularyRepository.save(vocabulary);
        wordRepository.save(word); // TODO А это зачем?
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
    public ResponseEntity<?> deleteWordById(long id) {
        if(wordRepository.findById(id).isPresent()){
            wordRepository.deleteById(id);
            return new ResponseEntity<>("The world delete.", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The word being deleted does not exist", HttpStatus.BAD_REQUEST);
        }
    }
}
