package ru.itpark.projectservice.infrastructure.repositories.project.custom;

import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.domain.valueobjects.DateInfo;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.tables.Projects;

@Repository
public class CustomProjectsRepository {
     private final DSLContext dsl;

    public CustomProjectsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

     public Project createProject(ProjectCreateCommand command) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        LocalDateTime dateInfoCreatedAt = null;
        LocalDateTime dateInfoDeletedAt = null;

        startDate = command.getStartDate() != null 
            ? command.getStartDate() 
            : now;

        endDate = command.getEndDate();

        if(command.getDateInfo() == null) {

            dateInfoCreatedAt = now;
        } else {

            dateInfoCreatedAt = command.getDateInfo().getCreatedAt();
            dateInfoDeletedAt = command.getDateInfo().getDeletedAt();
        }

        DateInfo dateInfo = command.getDateInfo();
        if (dateInfo == null) {
            dateInfo = DateInfo.builder()
                               .createdAt(now)
                               .build();
        }

        Long projectId = dsl.insertInto(Projects.PROJECTS)
                .set(Projects.PROJECTS.NAME, command.getName())
                .set(Projects.PROJECTS.DESCRIPTION, command.getDescription())
                .set(Projects.PROJECTS.START_DATE, startDate)
                .set(Projects.PROJECTS.END_DATE, command.getEndDate())
                .set(Projects.PROJECTS.STATUS, command.getStatus().name())
                .set(Projects.PROJECTS.USER_ID, command.getOwnerId())
                .set(Projects.PROJECTS.CREATED_AT, dateInfoCreatedAt)
                .set(Projects.PROJECTS.DELETED_AT, dateInfoDeletedAt)
                .returning(Projects.PROJECTS.ID)
                .fetchOne()
                .getValue(Projects.PROJECTS.ID);

        return Project.builder()
                .id(projectId)
                .name(command.getName())
                .description(command.getDescription())
                .startDate(startDate)
                .endDate(command.getEndDate())
                .status(command.getStatus())
                .ownerId(command.getOwnerId())
                .dateInfo(DateInfo.builder()
                    .createdAt(dateInfoCreatedAt)
                    .deletedAt(dateInfoDeletedAt)
                    .build())
                .build();
    }
}
