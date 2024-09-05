package ru.itpark.chatservice.presentation.chat.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateChatCommand {
    private Long projectId;
    private String projectName;
}
