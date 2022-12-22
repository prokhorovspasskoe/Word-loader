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
@Schema(description = "Информация о пользователе")
public class UserDto {
    private Long id;

    @Schema(description = "Логин пользователя")
    private String username;

    @Schema(description = "Словари пользователя")
    private List<VocabularyDto> vocabularies;

    @Schema(description = "Настройки режима изучения")
    private List<StudySettingDto> studySettings;

    @Schema(description = "Все планы изучения пользователя.")
    private List<StudyPlanDto> studyPlans;
}
