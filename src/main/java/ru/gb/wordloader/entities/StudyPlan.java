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
    private List<StudyProgress> studyProgress;

    //Реализовать метод, который вычисляет количество выученных слов в словаре "на лету"
    //согласно текущей настройки StudySettings.correctAttemptsRequired
    public Long getWordsFinished() {
        return Long.valueOf(0);
    }

}
