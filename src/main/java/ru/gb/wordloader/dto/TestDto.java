package ru.gb.wordloader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс для передачи на фронт данных для тестирования
 *
 * */


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {

    private Long studyPlan_id;
    private List<WordDto> words;
}
