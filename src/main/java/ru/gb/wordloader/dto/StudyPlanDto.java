package ru.gb.wordloader.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "План изучения.")
public class StudyPlanDto {
    @Schema(description = "id плана.")
    private Long id;

    @Schema(description = "id пользователя которому данный план пренадлежит.")
    private Long user_id;

    @Schema(description = "id словаря плана.")
    private Long vocabulary_id;

    @Schema(description = "Дата последнего теста по словарю.")
    private LocalDateTime lastProgress;
    @Schema(description = "Список слов для изучения из словаря.")
    private List<StudyWordDto> studyWords;
}
