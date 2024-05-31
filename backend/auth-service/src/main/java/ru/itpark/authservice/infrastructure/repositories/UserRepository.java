package ru.itpark.authservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.authservice.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);
  
}