package ru.itpark.projectservice.presentation.projects;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.itpark.projectservice.application.service.KafkaService;
import ru.itpark.projectservice.application.service.ProjectService;
import ru.itpark.projectservice.application.service.userproject.UserProjectService;
import ru.itpark.projectservice.domain.UserResponse;
import ru.itpark.projectservice.domain.project.valueobjects.DateInfo;
import ru.itpark.projectservice.domain.project.valueobjects.Status;
import ru.itpark.projectservice.infrastructure.kafka.InvitationMessage;
import ru.itpark.projectservice.infrastructure.mapper.ProjectMapper;
import ru.itpark.projectservice.presentation.projects.dto.command.ProjectCreateCommand;
import ru.itpark.projectservice.presentation.projects.dto.query.ProjectResponse;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectsController {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    UserProjectService userProjectService;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    KafkaService kafkaService;

    @RequestMapping("/alltest")
    public List<ProjectResponse> getAllTest() {
        return List.of(
            ProjectResponse.builder()
                .id(123456789L)
                .name("project name")
                .description("project description\n Этот мок проект реально пришел из сервера")
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
    public ProjectResponse createProject(@RequestBody ProjectCreateCommand projectCreateCommand) {

        return projectMapper.toResponse(
                projectService.save(projectCreateCommand)
        );
    }

    @GetMapping("/all")
    public List<ProjectResponse> getAll() {

        return projectMapper.toListResponse(projectService.getAll());
    }
    
    
    /**
     *
     * @param projectId Идентификатор проекта, для которого нужно
     *                  найти список список объектов, содержащих
     *                  электронные адреса участников
     * @return список объектов, содержащих электронные адреса участников
     */
    @GetMapping("/all/{projectId}")
    public List<UserResponse> getUsersForProjectId(@PathVariable long projectId) {
        
        return userProjectService.getAllForProjectId(projectId)
               .stream()
               .map(userProject -> UserResponse.builder()
                                   .email(userProject.getEmail())
                                   .build())
               .toList();
    }
    
    @RequestMapping(value = "/message", method=RequestMethod.POST)
    public void sendNotification(@RequestBody InvitationMessage notificationMessage) {

        kafkaService.sendNotificationMessage("notification-topic", notificationMessage);
    }
}
