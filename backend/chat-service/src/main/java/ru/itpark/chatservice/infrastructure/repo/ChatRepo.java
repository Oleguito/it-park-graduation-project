package ru.itpark.chatservice.infrastructure.repo;

import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.itpark.chatservice.domain.chat.Chat;

import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long>, JpaSpecificationExecutor<Chat> {
    @Nullable
    Chat findByProjectId(Long projectId);
}
