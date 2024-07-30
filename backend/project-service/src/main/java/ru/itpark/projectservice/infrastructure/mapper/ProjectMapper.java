package ru.itpark.projectservice.infrastructure.mapper;

import org.mapstruct.Mapper;
import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectQuery;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectQuery toQuery(Project project);

    List<ProjectQuery> toListQuery(List<Project> projects);
}
