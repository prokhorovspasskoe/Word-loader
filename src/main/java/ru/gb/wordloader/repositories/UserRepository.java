package ru.gb.wordloader.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
