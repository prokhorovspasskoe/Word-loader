package ru.gb.wordloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Список слов для изучения в одном тесте.")
public class TestDto {

    @Schema(description = "id плана изучения.")
    private Long studyPlan_id;

    @Schema(description = "Список изучаемых слов.")
    private List<WordDto> words;
}
