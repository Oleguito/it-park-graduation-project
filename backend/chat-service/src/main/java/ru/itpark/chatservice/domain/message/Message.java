package ru.itpark.chatservice.domain.message;

import jakarta.persistence.*;
import lombok.*;
import ru.itpark.chatservice.domain.chat.Chat;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "messages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq_gen")
    @SequenceGenerator(sequenceName = "message_seq_gen", name = "message_seq_gen", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "chat")
    private Chat chat;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

    @Column(name = "message")
    private String message;

}
