package ru.itpark.chatservice.presentation.chat.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatResponse {

    private Long chatId;
    private Long projectId;
    private String projectName;


}
