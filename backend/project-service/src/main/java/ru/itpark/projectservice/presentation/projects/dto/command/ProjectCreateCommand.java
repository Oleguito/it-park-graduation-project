package ru.itpark.projectservice.presentation.projects.dto.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.projectservice.domain.project.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.project.valueobjects.Status;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateCommand {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private Long ownerId;
    private DateInfo dateInfo;
    private String[] participantsEmails;
}
