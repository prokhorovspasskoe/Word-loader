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

    @Column(name="original")
    private String original;

    @Column(name="translated")
    private String translated;

    @ManyToMany(mappedBy = "words", fetch = FetchType.LAZY)
    private List<Vocabulary> vocabularies;

    //Переопределяем метод equals()
    //Сверяем сущности Word по ID, которые уникальны на уровне БД
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Word))
            return false;

        Word other = (Word) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
