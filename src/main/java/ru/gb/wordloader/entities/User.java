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

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Vocabulary> vocabularies;


    @OneToMany
    @JoinColumn(name = "user_id")
    private List<StudySetting> studySettings;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<StudyPlan> studyPlans;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    //Переопределяем метод equals()
    //Сверяем сущности User по ID, которые уникальны на уровне БД
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User))
            return false;

        User other = (User) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
