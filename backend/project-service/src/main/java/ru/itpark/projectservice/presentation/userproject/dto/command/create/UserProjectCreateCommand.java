package ru.itpark.projectservice.presentation.userproject.dto.command.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProjectCreateCommand {
    
    private String email;
    
    private Long projectId;
}
