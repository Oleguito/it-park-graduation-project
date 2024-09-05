package ru.itpark.chatservice.presentation.message.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMessageCommand {

    private Long chatId;
    private Long userId;
    private String username;
    private String message;

}
