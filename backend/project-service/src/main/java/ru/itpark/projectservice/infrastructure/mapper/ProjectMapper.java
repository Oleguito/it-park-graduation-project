package ru.itpark.projectservice.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectResponse;

import java.util.List;

@Mapper
public interface ProjectMapper {
    
    ProjectResponse toResponse(Project project);
    
    ProjectResponse toResponse(ProjectCreateCommand projectCreateCommand);
    
    List<ProjectResponse> toListResponse(List<Project> projects);
    
    // Project toProject(ProjectCreateCommand projectCreateCommand);
    
    // ParticipantProjectId toParticipantProjectId(Long participantId, Long projectId);
}
