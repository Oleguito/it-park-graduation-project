package ru.itpark.chatservice.infrastructure.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.chatservice.domain.message.Message;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByChat_id(Long chat_id);
}
