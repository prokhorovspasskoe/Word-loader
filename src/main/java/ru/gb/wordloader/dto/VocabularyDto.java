package ru.gb.wordloader.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Словарь.")
public class VocabularyDto {
    @Schema(description = "id словаря.")
    private Long id;

    @Schema(description = "Тема словаря.")
    private String theme;

    @Schema(description = "Общедоступность словаря.")
    private boolean isPrivate;

    @Schema(description = "Слова.")
    private List<WordDto> words;

}
