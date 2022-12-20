package ru.gb.wordloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Настройки режима изучения.")
public class StudySettingDto {
    @Schema(description = "id пользователя которому пренадлежат настройки.")
    private Long user_id;

    @Schema(description = "Тема изучаемого словаря.")
    private String theme;

    @Schema(description = "id изучаемого словаря.")
    private Long vocabulary_id;
    @Schema(description = "Минимальный промежуток в минутах после последнего теста.")
    private int minBreakPeriod;

    @Schema(description = "Количество правильных ответов.")
    private int correctAttemptsRequired;

    @Schema(description = "Количество изучаемых слов в тесте из словаря.")
    private int wordsInTest;
}
