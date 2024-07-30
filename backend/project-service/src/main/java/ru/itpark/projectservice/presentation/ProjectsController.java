package ru.itpark.projectservice.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itpark.projectservice.application.service.ProjectService;
import ru.itpark.projectservice.domain.Project;
import ru.itpark.projectservice.domain.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.mapper.ProjectMapper;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectQuery;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectsController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @GetMapping("/alltest")
    public List<ProjectQuery> getAllTest() {
        return List.of(
                ProjectQuery.builder()
                        .id(123456789L)
                        .name("project name")
                        .description("project description\n Этот проект реально пришел из сервера")
                        .status(Status.NEW)
                        .dateInfo(DateInfo.builder()
                                        .createdAt(LocalDateTime.now())
                                .build())
                        .endDate(LocalDateTime.now().plusDays(3L))
                        .ownerId(987564321L)
                .build()
        );
    }

    @PostMapping("/add")
    public ProjectQuery createProject(@RequestBody ProjectCreateCommand projectCreateCommand) {

        return projectMapper.toQuery(
                projectService.save(projectMapper.toProject(projectCreateCommand))
        );
    }

    @GetMapping("/all")
    public List<ProjectQuery> getAll() {

        return projectMapper.toListQuery(projectService.getAll());
    }
}
