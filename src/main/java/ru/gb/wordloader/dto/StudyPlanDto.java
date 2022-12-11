package ru.gb.wordloader.dto;


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
public class StudyPlanDto {

    private Long id;

    private Long user_id;

    private Long vocabulary_id;

    private LocalDateTime lastProgress;

    private List<StudyWordDto> studyWords;
}
