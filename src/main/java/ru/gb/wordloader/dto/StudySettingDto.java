package ru.gb.wordloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.wordloader.entities.User;
import ru.gb.wordloader.entities.Vocabulary;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudySettingDto {

    private String theme;

    private int minBreakPeriod;

    private int correctAttemptsRequired;

    private int wordsInTest;
}
