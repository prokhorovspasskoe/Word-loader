package ru.gb.wordloader.entities;

/**
 * Сущность Vocabulary - словарь изучаемых слов
 * Атрибуты :
 * id - идентификатор
 * theme - тема словаря, некое текстовое описание, 255 символов максимум
 * isPrivate - настройка, указываемая пользователем, означающая, будет словарь доступен только пользователю, его
 *             создавшему или всем. Если словарь пользователем сделан общедоступным, то сделать его приватным обратно
 *             можно только если он не используется другими пользователями
 * user - пользователь, создавший словарь
 * words - список слов словаря
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
@Table(name="vocabularies")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "is_private")
    private boolean isPrivate;

    @ManyToOne
    @JoinColumn(name = "user_id")
     private User user;

    @ManyToMany
    @JoinTable(
            name = "vocabulary_words",
            joinColumns = @JoinColumn(name = "vocabulary_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private List<Word> words;

}
