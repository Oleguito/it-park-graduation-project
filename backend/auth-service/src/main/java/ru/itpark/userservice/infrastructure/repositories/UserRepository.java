package ru.itpark.userservice.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itpark.userservice.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByLogin(String login);
  
}