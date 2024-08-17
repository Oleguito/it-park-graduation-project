package ru.itpark.notificationservice.presentation.notification.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateCommand {
    private Long id;
    private Long userId;
    private String title;
    private String message;
    private String email;
    private String type;
    private Boolean read;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
