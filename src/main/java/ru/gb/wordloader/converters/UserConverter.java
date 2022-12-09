package ru.gb.wordloader.converters;

import ru.gb.wordloader.dto.UserDto;
import ru.gb.wordloader.entities.User;

public class UserConverter {

    /*
    public User convertFromDtoToEntity(UserDto userDto){
        User user = new User();
        user.setName(user.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto convertFromEntityToDto(User user){
       UserDto userDto = new UserDto();
       userDto.setUsername(user.getName());
       userDto.setPassword(user.getPassword());
       return userDto;
    }
    */
    public static UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getName())
                .vocabularies(VocabularyConverter.convertToDtoList(user.getVocabularies()))
                .studyPlans(StudyPlanConverter.convertToDtoList(user.getStudyPlans()))
                .build();
    }


}
