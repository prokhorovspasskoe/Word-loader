package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

public class UserConverter {

    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getName())
                .vocabularies(VocabularyConverter.convertToDtoList(user.getVocabularies()))
                //.studyPlans(StudyPlanConverter.convertToDtoList(user.getStudyPlans()))
                //.studySettings(StudySettingConverter.convertToDtoList(user.getStudySettings()))
                .build();
    }
}
