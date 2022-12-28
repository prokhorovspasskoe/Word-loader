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
@Schema(description = "Словарь. Слово")
public class VocabularyWordDto {

    @Schema(description = "id словаря.")
    private Long vocabularyId;

    @Schema(description = "Оригинал на английском.")
    private String original;

    @Schema(description = "Перевод на русский.")
    private String translated;
}
