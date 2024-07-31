package ru.itpark.authservice.infrastructure.repositories.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itpark.authservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;

public interface UserRepository extends JpaRepository<User, Long> {

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO users (full_name, email, login, languages, role, created_at, deleted_at) " +
          "VALUES (:fullName, :email, :login, cast(:languages as jsonb), :role, :createdAt,  :deletedAt)", nativeQuery = true)
  User saveUser(@Param("fullName") String fullName,
                @Param("email") String email,
                @Param("login") String login,
                @Param("languages") String languages,
                @Param("role") String role,
                @Param("createdAt") LocalDateTime createdAt,
                @Param("deletedAt") LocalDateTime deletedAt);

  Optional<User> findByEmail(String login);
}