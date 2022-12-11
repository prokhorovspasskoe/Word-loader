package ru.gb.wordloader.entities;

/**
 * Сущность User - пользователь приложения.
 * Атрибуты :
 * id, name, password - идентификатор, имя и пароль
 * vocabularies - список словарей, созданных пользователем
 * studySettings - список настроек режимов изучения пользователя. Отдельная настройка для каждого словаря
 * studyPlans - список словарей, изучаемых пользователем. Один элемент - изучаемый словарь с текущим прогрессом
 * roles - список ролей пользователя
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
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_vocabularies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "vocabulary_id")
    )
    private List<Vocabulary> vocabularies;

/*
    @OneToMany
    private List<StudySetting> studySettings;

    @OneToMany
    private List<StudyPlan> studyPlans;
*/

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


}
