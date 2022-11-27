package ru.gb.wordloader.entities;

/**
 * Сущность Word - изучаемое слово
 * Атрибуты :
 * id - идентификатор
 * original - значение слова на изучаемом языке (английском)
 * translated - значение слова на родном языке (русском)
 * vocabularies - список словарей, где используется слово
 *
 * Слова хранятся в БД с таблице words, в которой создан индекс на уникальность поля original.
 *
 * */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String original;

    @Column(name="password")
    private String translated;

    @ManyToMany
    @JoinTable(
            name = "vocabulary_words",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "vocabulary_id")
    )
    private List<Vocabulary> vocabularies;
}
