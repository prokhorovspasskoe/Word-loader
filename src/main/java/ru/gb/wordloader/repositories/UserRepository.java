package ru.gb.wordloader.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.wordloader.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
