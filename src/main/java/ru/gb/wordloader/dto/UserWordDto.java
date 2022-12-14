package ru.gb.wordloader.dto;

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
public class UserWordDto {

    private Long studyPlan_id;
    private Long word_id;
    private String userTypedValue;
}
