package ru.itpark.projectservice.infrastructure.repositories.project.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.itpark.projectservice.domain.project.Project;
import ru.itpark.projectservice.domain.project.valueobjects.DateInfo;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.tables.Projects;

import java.time.LocalDateTime;

@Repository
public class CustomProjectsRepository {
    private final DSLContext dsl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomProjectsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Project createProject(Project project) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        LocalDateTime dateInfoCreatedAt = null;
        LocalDateTime dateInfoDeletedAt = null;

        startDate = project.getStartDate() != null
                ? project.getStartDate()
                : now;

        endDate = project.getEndDate();

        if (project.getDateInfo() == null) {

            dateInfoCreatedAt = now;
        } else {

            dateInfoCreatedAt = project.getDateInfo().getCreatedAt();
            dateInfoDeletedAt = project.getDateInfo().getDeletedAt();
        }

        DateInfo dateInfo = project.getDateInfo();
        if (dateInfo == null) {
            dateInfo = DateInfo.builder()
                    .createdAt(now)
                    .build();
        }

        Long projectId = dsl.insertInto(Projects.PROJECTS)
                .set(Projects.PROJECTS.NAME, project.getName())
                .set(Projects.PROJECTS.DESCRIPTION, project.getDescription())
                .set(Projects.PROJECTS.START_DATE, startDate)
                .set(Projects.PROJECTS.END_DATE, project.getEndDate())
                .set(Projects.PROJECTS.STATUS, project.getStatus().name())
                .set(Projects.PROJECTS.OWNER_EMAIL, project.getOwnerEmail())
                .set(Projects.PROJECTS.CREATED_AT, dateInfoCreatedAt)
                .set(Projects.PROJECTS.DELETED_AT, dateInfoDeletedAt)

                .returning(Projects.PROJECTS.ID)
                .fetchOne()
                .getValue(Projects.PROJECTS.ID);

        return Project.builder()
                .id(projectId)
                .name(project.getName())
                .description(project.getDescription())
                .startDate(startDate)
                .endDate(project.getEndDate())
                .status(project.getStatus())
                .ownerEmail(project.getOwnerEmail())
                .dateInfo(DateInfo.builder()
                        .createdAt(dateInfoCreatedAt)
                        .deletedAt(dateInfoDeletedAt)
                        .build())
                .build();
    }
}
