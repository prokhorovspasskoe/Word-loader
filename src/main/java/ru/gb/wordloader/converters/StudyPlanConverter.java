package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.StudyPlanDto;
import ru.gb.wordloader.dto.StudyWordDto;
import ru.gb.wordloader.entities.StudyPlan;
import ru.gb.wordloader.entities.StudyProgress;
import ru.gb.wordloader.entities.Word;

import java.util.*;
import java.util.stream.Collectors;

public class StudyPlanConverter {

    private static Map<Word, Integer> getWordsStudyProgress(List<StudyProgress> studyProgress) {
        Map<Word, Integer> wordsProgress = new HashMap<>();

        ListIterator<StudyProgress> listIterator = studyProgress.listIterator();
        while( listIterator.hasNext() ) {
            StudyProgress wordProgress = listIterator.next();

            Integer oldCount = wordsProgress.get(wordProgress.getWord());
            if ( oldCount == null ) {
                oldCount = 0;
            }
            wordsProgress.put(wordProgress.getWord(), oldCount + 1);
        }
        return wordsProgress;
    }

    private static StudyWordDto convertToStudyWordDto(Word word, Integer correctAnswers) {
        return StudyWordDto.builder()
                .id(word.getId())
                .original(word.getOriginal())
                .translated(word.getTranslated())
                .correctAnswers(correctAnswers)
                .build();
    }

    public static StudyPlanDto convertToDto(StudyPlan studyPlan) {

        /*
        * Получаем мапу с прогрессом по изучению слов в формате "Слово" - "количество успешных попыток"
        * */
        Map<Word, Integer> wordsProgress = getWordsStudyProgress(studyPlan.getStudyProgress());

        List<StudyWordDto> studyWordDtos = new ArrayList<>();
        for(Map.Entry<Word, Integer> entry : wordsProgress.entrySet()){
            studyWordDtos.add(convertToStudyWordDto(entry.getKey(), entry.getValue()));
        }

        return StudyPlanDto.builder()
                .id(studyPlan.getId())
                .user_id(studyPlan.getUser().getId())
                .vocabulary_id( studyPlan.getVocabulary().getId() )
                .studyWords(studyWordDtos)
                .lastProgress(studyPlan.getLastProgress())
                .build();
    }

    public static List<StudyPlanDto> convertToDtoList(List<StudyPlan> studyPlans) {
        return studyPlans.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }


    /**
     * Необходимость в обратных методах пока не вижу
     * */

}
