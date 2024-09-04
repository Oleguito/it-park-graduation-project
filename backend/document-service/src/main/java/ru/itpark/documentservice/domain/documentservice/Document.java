package ru.itpark.documentservice.domain.documentservice;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "document")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "documents_seq_gen")
    @SequenceGenerator(sequenceName = "documents_seq_gen", name = "documents_seq_gen", allocationSize = 1)
    private Long id;

    @Column(name = "file_name", length = 500)
    private String fileName;

    @Column(name = "link", length = 1000)
    private String link;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
