package ru.itpark.projectservice.presentation.projects;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.itpark.projectservice.application.service.KafkaService;
import ru.itpark.projectservice.application.service.ProjectService;
import ru.itpark.projectservice.domain.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;
import ru.itpark.projectservice.infrastructure.mapper.ProjectMapper;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectQuery;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectsController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    KafkaService kafkaService;

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
                projectService.save(projectCreateCommand)
        );
    }

    @GetMapping("/all")
    public List<ProjectQuery> getAll() {

        return projectMapper.toListQuery(projectService.getAll());
    }

    
    @RequestMapping(value = "/message", method=RequestMethod.POST)
    public void requestMethodName(@RequestBody InvitationMessage notificationMessage) {

        kafkaService.sendNotificationMessage("notification-topic", notificationMessage);
    }
}
