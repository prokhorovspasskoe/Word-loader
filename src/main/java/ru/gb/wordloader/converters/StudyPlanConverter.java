package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.StudyPlanDto;
import ru.gb.wordloader.entities.StudyPlan;

import java.util.List;
import java.util.stream.Collectors;

public class StudyPlanConverter {

    public static StudyPlanDto convertToDto(StudyPlan studyPlan) {

        return StudyPlanDto.builder()
                .id(studyPlan.getId())
                .vocabulary(VocabularyConverter.convertToDto(studyPlan.getVocabulary()))
                .lastProgress(studyPlan.getLastProgress())
                .wordsFinished(studyPlan.getWordsFinished())
                .build();
    }

    public static List<StudyPlanDto> convertToDtoList(List<StudyPlan> studyPlans) {
        return studyPlans.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }


    /**
     * Необходимость в обратных методах пока не вижу
     * */

}
