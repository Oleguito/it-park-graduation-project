package ru.itpark.notificationservice.presentation.notification.dto.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationQuery {
    private Long id;
    private Long userId;
    private String title;
    private String message;
    private String type;
    private Boolean read;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Pageable pageable;
}
