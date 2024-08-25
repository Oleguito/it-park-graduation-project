package ru.itpark.projectservice.presentation.userproject.dto.command.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProjectDeleteCommand {
    
    private String email;
    
    private Long projectId;
}
