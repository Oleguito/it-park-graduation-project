package ru.itpark.chatservice.domain.chat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chats")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq_gen")
    @SequenceGenerator(sequenceName = "chat_seq_gen", name = "chat_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private Long projectName;

}
