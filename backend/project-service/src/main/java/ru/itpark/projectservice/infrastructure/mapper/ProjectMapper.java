package ru.itpark.projectservice.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectQuery;

import java.util.List;

@Mapper
public interface ProjectMapper {
    
    ProjectQuery toQuery(Project project);
    
    ProjectQuery toQuery(ProjectCreateCommand projectCreateCommand);
    
    List<ProjectQuery> toListQuery(List<Project> projects);
    
    // Project toProject(ProjectCreateCommand projectCreateCommand);
    
    // ParticipantProjectId toParticipantProjectId(Long participantId, Long projectId);
}
