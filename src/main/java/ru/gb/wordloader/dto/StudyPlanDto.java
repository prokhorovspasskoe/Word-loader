package ru.gb.wordloader.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPlanDto {

    private Long id;

    private VocabularyDto vocabulary;

    private LocalDateTime lastProgress;

    private Long wordsFinished;

}
