package ru.gb.wordloader.entities;

/**
 * Сущность StudyProgress - одна успешная итерация по отдельному слову изучаемого словаря
 * Атрибуты :
 * id - идентификатор
 * studyPlan - изучаемый пользователем словарь
 * word - слово, по которому прошла успешная итерация изучения
 *
 * */


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
@Table(name="study_progress")
public class StudyProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_plan_id")
    private StudyPlan studyPlan;


    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
}
