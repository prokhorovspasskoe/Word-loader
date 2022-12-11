package ru.gb.wordloader.entities;

/**
 * Сущность StudyPlan - изучаемый пользователем словарь с текущим прогрессом
 * Атрибуты :
 * id - идентификатор
 * user - пользователь, который изучает данный словарь
 * vocabulary - изучаемый словарь
 * lastProgress - время последней итерации изучения. Используется в комплексе с StudySetting.minBreakPeriod для определения
 *                момента времени, когда доступна следующая итерация изучения
 * studyProgress - список значений - текущий прогресс по изучению слов словаря. Каждое элемент studyProgress представляет
 *                 собой одну успешную итерацию по отдельному слову
 * */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="study_plans")
public class StudyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id")
    private Vocabulary vocabulary;

    @Column(name="last_progress")
    private LocalDateTime lastProgress;

    @OneToMany
    @JoinColumn(name="study_plan_id")
    private List<StudyProgress> studyProgress;
}
