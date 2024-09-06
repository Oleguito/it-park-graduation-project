package ru.itpark.projectservice.infrastructure.chatservice.dto.command;

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
