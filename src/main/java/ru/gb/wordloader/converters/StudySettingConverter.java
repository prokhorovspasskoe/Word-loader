package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.StudyPlanDto;
import ru.gb.wordloader.dto.StudySettingDto;
import ru.gb.wordloader.dto.WordDto;
import ru.gb.wordloader.entities.StudyPlan;
import ru.gb.wordloader.entities.StudySetting;

import java.util.List;
import java.util.stream.Collectors;


public class StudySettingConverter {

    public static StudySettingDto convertToDto(StudySetting studySetting) {
        return StudySettingDto.builder()
                .user_id(studySetting.getUser().getId())
                .vocabulary_id(studySetting.getVocabulary().getId())
                .correctAttemptsRequired(studySetting.getCorrectAttemptsRequired())
                .minBreakPeriod(studySetting.getMinBreakPeriod())
                .wordsInTest(studySetting.getWordsInTest())
                .build();
    }

    public static List<StudySettingDto> convertToDtoList(List<StudySetting> studySettings) {
        return studySettings.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
    }
}
