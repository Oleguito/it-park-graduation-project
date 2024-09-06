package ru.itpark.chatservice.presentation.message.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private Long chatId;
    private Long userId;
    private String username;
    private LocalDateTime sentAt;
    private String message;
    private String uuid;

}
