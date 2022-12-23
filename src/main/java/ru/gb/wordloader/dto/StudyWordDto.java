package ru.gb.wordloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Изучаемое слово.")
public class StudyWordDto {

    @Schema(description = "id изучаемого слова.")
    private Long id;

    @Schema(description = "Оригинал на английском.")
    private String original;
    @Schema(description = "Перевод на русский.")
    private String translated;
    @Schema(description = "Количество верных ответов.")
    private Integer correctAnswers;
}
