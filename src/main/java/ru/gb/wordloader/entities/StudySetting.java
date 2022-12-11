package ru.gb.wordloader.entities;

/**
 * Сущность StudySetting - настройка режима изучения словаря пользователем
 * Атрибуты :
 * id - идентификатор
 * user - пользователь, который создал данную настройку
 * vocabulary - словарь, по которому сделана настройка
 * minBreakPeriod - минимальное количество минут, которое должно пройти с момента предыдущей итерации
 *                  изучения слов
 * wordsInTest - количество слов, которое будет предлагаться пользователю в ходе отдельной итерации изучения
 * correctAttemptsRequired - количество успешных попыток, после которых слово будет считаться выученным и не будет
 *                           далее предлагаться в дальнейших итерациях изучения
 * * */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="study_settings")
public class StudySetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vocabulary_id")
    private Vocabulary vocabulary;

    @Column(name = "min_break_period")
    private int minBreakPeriod;

    @Column(name = "correct_attempts_required")
    private int correctAttemptsRequired;

    @Column(name = "words_in_test")
    private int wordsInTest;
}
