package ru.gb.wordloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.wordloader.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role_user);
}
