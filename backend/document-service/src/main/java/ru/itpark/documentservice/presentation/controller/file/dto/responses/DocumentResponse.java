package ru.itpark.documentservice.presentation.controller.file.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentResponse implements Serializable {
    private String fileName;
    private String link;
    private Long projectId;
    private Long userId;
    private LocalDateTime createdAt;
}