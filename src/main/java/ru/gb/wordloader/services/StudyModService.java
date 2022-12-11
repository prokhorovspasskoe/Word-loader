package ru.gb.wordloader.services;

import ru.gb.wordloader.dto.WordDto;

import java.util.List;

public interface StudyModService {
    List<WordDto> initialize(String theme, int minBreakPeriod, int correctAttemptsRequired, int wordsInTest);

}
