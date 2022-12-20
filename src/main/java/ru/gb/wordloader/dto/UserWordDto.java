package ru.gb.wordloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Используется при получении данных с фронта о введённом значении пользователем в ходе теста.
 * Содержит сведения об изучаемом в тесте словаре (studyPlan_id), текущем слове (word_id),
 * введённом пользователем значении (userTypedValue)
 *
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Ответ пользователя в тесте.")
public class UserWordDto {
    @Schema(description = "id плана изучения.")
    private Long studyPlan_id;

    @Schema(description = "id изучаемого слова")
    private Long word_id;

    @Schema(description = "Слово из ответа пользователя.")
    private String userTypedValue;
}
